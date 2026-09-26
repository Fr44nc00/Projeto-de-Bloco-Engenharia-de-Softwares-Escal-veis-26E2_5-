package com.francobbs.lojavideogames.application;

import com.francobbs.lojavideogames.domain.Compra;
import com.francobbs.lojavideogames.domain.CompraEvent;
import com.francobbs.lojavideogames.domain.Usuario;
import com.francobbs.lojavideogames.domain.Jogo;
import com.francobbs.lojavideogames.infrastructure.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import static com.francobbs.lojavideogames.infrastructure.RabbitConfig.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompraService {
    private final CompraRepository compraRepository;
    private final UsuarioClient usuarioClient;
    private final JogoClient jogoClient;
    private final RabbitTemplate rabbitTemplate;

    public CompraService(CompraRepository compraRepository,
                         UsuarioClient usuarioClient,
                         JogoClient jogoClient,
                         RabbitTemplate rabbitTemplate) {
        this.compraRepository = compraRepository;
        this.usuarioClient = usuarioClient;
        this.jogoClient = jogoClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<Compra> listar() { return compraRepository.findAll(); }
    public Compra buscarPorId(Long id) { return compraRepository.findById(id).orElse(null); }

    @Transactional
    public Compra cadastrar(Compra compra) {
        Usuario usuario = usuarioClient.buscarPorId(
                compra.getUsuario().getId()
        );

        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado");
        }
        compra.setUsuario(usuario);

        List<Jogo> jogos = compra.getJogos().stream()
                .map(j -> {
                    Jogo jogo = jogoClient.buscarPorId(j.getId());

                    if (jogo == null) {
                        throw new RuntimeException("Jogo não encontrado");
                    }

                    return jogo;
                })
                .toList();
        compra.setJogos(jogos);

        double total = jogos.stream().mapToDouble(Jogo::getPreco).sum();
        compra.setValorTotal(total);
        compra.setDataHora(LocalDateTime.now());

        Compra compraSalva = compraRepository.save(compra);

        CompraEvent evento = new CompraEvent(
                compraSalva.getId(),
                compraSalva.getUsuario().getId(),
                compraSalva.getJogos().stream().map(Jogo::getId).toList(),
                compraSalva.getValorTotal(),
                compraSalva.getDataHora()
        );

        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE_NAME,
                RabbitConfig.ROUTING_KEY,
                evento
        );

        return compraSalva;
    }

    @Transactional
    public Compra atualizar(Long id, Compra compra) {
        compra.setId(id);
        return cadastrar(compra);
    }

    @Transactional
    public boolean deletar(Long id) {
        if (compraRepository.existsById(id)) {
            compraRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Compra> buscarPorUsuario(Long usuarioId) {
        return compraRepository.findByUsuarioId(usuarioId);
    }

    public List<Compra> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return compraRepository.findByDataHoraBetween(inicio, fim);
    }

    public List<Compra> buscarPorValorMinimo(Double valor) {
        return compraRepository.findByValorTotalGreaterThanEqual(valor);
    }
}

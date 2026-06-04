package com.francobbs.lojavideogames.application;

import com.francobbs.lojavideogames.domain.Compra;
import com.francobbs.lojavideogames.domain.Usuario;
import com.francobbs.lojavideogames.domain.Jogo;
import com.francobbs.lojavideogames.infrastructure.CompraRepository;
import com.francobbs.lojavideogames.infrastructure.UsuarioRepository;
import com.francobbs.lojavideogames.infrastructure.JogoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompraService {
    private final CompraRepository compraRepository;
    private final UsuarioRepository usuarioRepository;
    private final JogoRepository jogoRepository;

    public CompraService(CompraRepository compraRepository,
                         UsuarioRepository usuarioRepository,
                         JogoRepository jogoRepository) {
        this.compraRepository = compraRepository;
        this.usuarioRepository = usuarioRepository;
        this.jogoRepository = jogoRepository;
    }

    public List<Compra> listar() {
        return compraRepository.findAll();
    }

    public Compra buscarPorId(Long id) {
        return compraRepository.findById(id).orElse(null);
    }

    public Compra cadastrar(Compra compra) {
        Usuario usuario = usuarioRepository.findById(compra.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        compra.setUsuario(usuario);

        List<Jogo> jogos = compra.getJogos().stream()
                .map(j -> jogoRepository.findById(j.getId())
                        .orElseThrow(() -> new RuntimeException("Jogo não encontrado")))
                .toList();
        compra.setJogos(jogos);

        double total = jogos.stream().mapToDouble(Jogo::getPreco).sum();
        compra.setValorTotal(total);

        compra.setDataHora(LocalDateTime.now());

        return compraRepository.save(compra);
    }

    public Compra atualizar(Long id, Compra compra) {
        compra.setId(id);
        return cadastrar(compra);
    }

    public boolean deletar(Long id) {
        if (compraRepository.existsById(id)) {
            compraRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

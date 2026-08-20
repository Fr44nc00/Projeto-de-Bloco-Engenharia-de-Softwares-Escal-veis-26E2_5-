package com.francobbs.jogoservice.application;

import com.francobbs.jogoservice.domain.Jogo;
import com.francobbs.jogoservice.domain.Plataforma;
import com.francobbs.jogoservice.infrastructure.JogoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {
    private final JogoRepository repository;

    public JogoService(JogoRepository repository) {
        this.repository = repository;
    }

    public List<Jogo> listar() { return repository.findAll(); }
    public Jogo buscarPorId(Long id) { return repository.findById(id).orElse(null); }
    public Jogo cadastrar(Jogo jogo) { return repository.save(jogo); }
    public Jogo atualizar(Long id, Jogo jogo) {
        jogo.setId(id);
        return repository.save(jogo);
    }
    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Jogo> buscarPorPlataforma(Plataforma plataforma) {
        return repository.findByPlataforma(plataforma);
    }

    public List<Jogo> buscarPorTitulo(String titulo) {
        return repository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Jogo> buscarPorPrecoMaximo(Double preco) {
        return repository.findByPrecoLessThanEqual(preco);
    }
}

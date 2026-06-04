package com.francobbs.lojavideogames.application;

import com.francobbs.lojavideogames.domain.Jogo;
import com.francobbs.lojavideogames.infrastructure.JogoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {
    private final JogoRepository repository;

    public JogoService(JogoRepository repository) {
        this.repository = repository;
    }

    public List<Jogo> listar() {
        return repository.findAll();
    }

    public Jogo buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Jogo cadastrar(Jogo jogo) {
        return repository.save(jogo);
    }

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
}
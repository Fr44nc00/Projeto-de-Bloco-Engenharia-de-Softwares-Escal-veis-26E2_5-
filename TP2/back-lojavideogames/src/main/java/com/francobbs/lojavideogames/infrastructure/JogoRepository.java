package com.francobbs.lojavideogames.infrastructure;

import com.francobbs.lojavideogames.domain.Jogo;
import com.francobbs.lojavideogames.domain.Plataforma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long> {

    List<Jogo> findByPlataforma(Plataforma plataforma);

    List<Jogo> findByTituloContainingIgnoreCase(String titulo);

    List<Jogo> findByPrecoLessThanEqual(Double preco);
}


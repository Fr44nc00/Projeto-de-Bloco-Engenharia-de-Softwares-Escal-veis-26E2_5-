package com.francobbs.lojavideogames.infrastructure;

import com.francobbs.lojavideogames.domain.Jogo;
import com.francobbs.lojavideogames.domain.Plataforma;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class JogoRepositoryTest {

    @Autowired
    private JogoRepository jogoRepository;

    @Autowired
    private CompraRepository compraRepository;

    @BeforeEach
    void limparBanco() {
        compraRepository.deleteAll();
        jogoRepository.deleteAll();
    }

    @Test
    void deveSalvarEEncontrarPorPlataforma() {
        Jogo jogo = new Jogo(null, "Zelda", Plataforma.NINTENDO_SWITCH, 299.90);
        jogoRepository.save(jogo);

        List<Jogo> encontrados = jogoRepository.findByPlataforma(Plataforma.NINTENDO_SWITCH);

        assertThat(encontrados).hasSize(1);
        assertThat(encontrados.get(0).getTitulo()).isEqualTo("Zelda");
    }

    @Test
    void deveBuscarPorTituloIgnorandoCase() {
        Jogo jogo = new Jogo(null, "God of War", Plataforma.PLAYSTATION_5, 349.90);
        jogoRepository.save(jogo);

        List<Jogo> encontrados = jogoRepository.findByTituloContainingIgnoreCase("god");

        assertThat(encontrados).isNotEmpty();
        assertThat(encontrados.get(0).getTitulo()).isEqualTo("God of War");
    }

    @Test
    void deveBuscarPorPrecoMaximo() {
        Jogo barato = new Jogo(null, "Indie Game", Plataforma.XBOX_SERIES_X, 50.0);
        Jogo caro = new Jogo(null, "AAA Game", Plataforma.XBOX_SERIES_X, 300.0);
        jogoRepository.save(barato);
        jogoRepository.save(caro);

        List<Jogo> encontrados = jogoRepository.findByPrecoLessThanEqual(100.0);

        assertThat(encontrados).hasSize(1);
        assertThat(encontrados.get(0).getTitulo()).isEqualTo("Indie Game");
    }
}

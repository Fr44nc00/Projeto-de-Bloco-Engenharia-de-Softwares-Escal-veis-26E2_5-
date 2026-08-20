package com.francobbs.jogoservice.application;

import com.francobbs.jogoservice.domain.Jogo;
import com.francobbs.jogoservice.domain.Plataforma;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JogoServiceTest {

    @Autowired
    private JogoService jogoService;

    @Test
    void deveCadastrarJogo() {

        Jogo jogo = new Jogo(
                null,
                "Super Mario",
                Plataforma.NINTENDO_SWITCH,
                299.90
        );

        Jogo salvo = jogoService.cadastrar(jogo);

        assertThat(salvo.getId()).isNotNull();
        assertThat(salvo.getTitulo()).isEqualTo("Super Mario");
    }

    @Test
    void deveBuscarJogosPorTitulo() {

        Jogo jogo = new Jogo(
                null,
                "God of War Ragnarok",
                Plataforma.PLAYSTATION_5,
                349.90
        );

        jogoService.cadastrar(jogo);

        assertThat(
                jogoService.buscarPorTitulo("God")
        ).isNotEmpty();
    }
}
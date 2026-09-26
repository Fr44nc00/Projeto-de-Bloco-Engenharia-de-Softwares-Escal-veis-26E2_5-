package com.francobbs.lojavideogames.infrastructure;

import com.francobbs.lojavideogames.domain.Jogo;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class JogoClient {

    private final RestTemplate restTemplate;

    public JogoClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Jogo buscarPorId(Long id) {
        return restTemplate.getForObject(
                "http://JOGO-SERVICE/jogos/" + id,
                Jogo.class
        );
    }
}
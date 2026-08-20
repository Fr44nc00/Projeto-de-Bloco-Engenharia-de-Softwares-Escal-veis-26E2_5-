package com.francobbs.lojavideogames.infrastructure;

import com.francobbs.lojavideogames.domain.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UsuarioClient {

    private final RestTemplate restTemplate;

    public UsuarioClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Usuario buscarPorId(Long id) {
        return restTemplate.getForObject(
                "http://USUARIO-SERVICE/usuarios/" + id,
                Usuario.class
        );
    }
}
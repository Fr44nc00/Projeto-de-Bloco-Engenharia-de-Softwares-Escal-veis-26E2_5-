package com.francobbs.usuarioservice.application;

import com.francobbs.usuarioservice.domain.TipoUsuario;
import com.francobbs.usuarioservice.domain.Usuario;
import com.francobbs.usuarioservice.infrastructure.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void limparBanco() {
        usuarioRepository.deleteAll();
    }

    @Test
    void deveCadastrarUsuario() {
        Usuario usuario = new Usuario(null, "Novo Usuário", "novo@teste.com", TipoUsuario.CLIENTE, "senha123");
        Usuario salvo = usuarioService.cadastrar(usuario);

        assertThat(salvo.getId()).isNotNull();
        assertThat(salvo.getEmail()).isEqualTo("novo@teste.com");
    }

    @Test
    void deveBuscarUsuarioPorNome() {
        Usuario usuario = new Usuario(null, "Carlos", "carlos@teste.com", TipoUsuario.CLIENTE, "senha456");
        usuarioRepository.save(usuario);

        List<Usuario> encontrados = usuarioService.buscarPorNome("Carlos");

        assertThat(encontrados).isNotEmpty();
        assertThat(encontrados.get(0).getNome()).isEqualTo("Carlos");
    }

    @Test
    void deveBuscarUsuarioPorEmail() {

        Usuario usuario = new Usuario(
                null,
                "João",
                "joao@teste.com",
                TipoUsuario.CLIENTE,
                "1234"
        );

        usuarioRepository.save(usuario);

        Usuario encontrado =
                usuarioService.buscarPorEmail("joao@teste.com");

        assertThat(encontrado).isNotNull();
        assertThat(encontrado.getEmail()).isEqualTo("joao@teste.com");
    }
}

package com.francobbs.lojavideogames.infrastructure;

import com.francobbs.lojavideogames.domain.Usuario;
import com.francobbs.lojavideogames.domain.TipoUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CompraRepository compraRepository;

    @BeforeEach
    void limparBanco() {
        compraRepository.deleteAll();
        usuarioRepository.deleteAll();
    }

    @Test
    void deveSalvarEEncontrarUsuarioPorEmail() {
        Usuario usuario = new Usuario(null, "Mateus", "mateus@teste.com", TipoUsuario.CLIENTE, "123456");
        usuarioRepository.save(usuario);

        Optional<Usuario> encontrado = usuarioRepository.findByEmail("mateus@teste.com");

        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getNome()).isEqualTo("Mateus");
    }

    @Test
    void deveBuscarUsuariosPorTipo() {
        Usuario cliente = new Usuario(null, "Cliente", "cliente@teste.com", TipoUsuario.CLIENTE, "123");
        Usuario funcionario = new Usuario(null, "Funcionario", "func@teste.com", TipoUsuario.FUNCIONARIO, "456");
        usuarioRepository.save(cliente);
        usuarioRepository.save(funcionario);

        List<Usuario> clientes = usuarioRepository.findByTipo(TipoUsuario.CLIENTE);

        assertThat(clientes).hasSize(1);
        assertThat(clientes.get(0).getEmail()).isEqualTo("cliente@teste.com");
    }
}

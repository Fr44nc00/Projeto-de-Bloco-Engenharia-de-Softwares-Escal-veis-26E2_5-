package com.francobbs.lojavideogames.interfaces;

import com.francobbs.lojavideogames.domain.Usuario;
import com.francobbs.lojavideogames.domain.Jogo;
import com.francobbs.lojavideogames.domain.Compra;
import com.francobbs.lojavideogames.application.HistoricoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historico")
@CrossOrigin("*")
public class HistoricoController {

    private final HistoricoService service;

    public HistoricoController(HistoricoService service) {
        this.service = service;
    }

    @GetMapping("/usuario/{id}")
    public List<Usuario> historicoUsuario(@PathVariable Long id) {
        return service.listarHistoricoUsuario(id);
    }

    @GetMapping("/jogo/{id}")
    public List<Jogo> historicoJogo(@PathVariable Long id) {
        return service.listarHistoricoJogo(id);
    }

    @GetMapping("/compra/{id}")
    public List<Compra> historicoCompra(@PathVariable Long id) {
        return service.listarHistoricoCompra(id);
    }
}

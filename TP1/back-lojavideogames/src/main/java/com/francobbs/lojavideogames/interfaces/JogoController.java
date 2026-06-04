package com.francobbs.lojavideogames.interfaces;

import com.francobbs.lojavideogames.domain.Jogo;
import com.francobbs.lojavideogames.application.JogoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogos")
@CrossOrigin("*")
public class JogoController {
    private final JogoService service;

    public JogoController(JogoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Jogo> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogo> buscarPorId(@PathVariable Long id) {
        Jogo jogo = service.buscarPorId(id);
        return jogo != null ? ResponseEntity.ok(jogo) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Jogo> cadastrar(@RequestBody Jogo jogo) {
        Jogo salvo = service.cadastrar(jogo);
        return ResponseEntity.ok(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogo> atualizar(@PathVariable Long id, @RequestBody Jogo jogo) {
        Jogo atualizado = service.atualizar(id, jogo);
        return atualizado != null ? ResponseEntity.ok(atualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = service.deletar(id);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
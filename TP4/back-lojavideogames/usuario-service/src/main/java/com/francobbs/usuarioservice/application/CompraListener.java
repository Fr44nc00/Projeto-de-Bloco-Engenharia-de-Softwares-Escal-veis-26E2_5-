package com.francobbs.usuarioservice.application;

import com.francobbs.usuarioservice.domain.CompraEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CompraListener {

    private final UsuarioService usuarioService;

    public CompraListener(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @RabbitListener(queues = "compraQueue")
    public void processarCompra(CompraEvent compraEvent) {
        var usuario = usuarioService.buscarPorId(compraEvent.getUsuarioId());
        if (usuario != null) {
            System.out.println("Usuário " + usuario.getNome() +
                    " realizou uma compra de R$" + compraEvent.getValorTotal() +
                    " em " + compraEvent.getDataHora());
        } else {
            System.out.println("Usuário não encontrado para compra " + compraEvent.getId());
        }
    }
}

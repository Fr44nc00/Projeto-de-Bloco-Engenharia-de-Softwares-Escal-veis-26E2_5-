package com.francobbs.jogoservice.application;

import com.francobbs.jogoservice.domain.CompraEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CompraListener {

    private final JogoService jogoService;

    public CompraListener(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @RabbitListener(queues = "compraQueue")
    public void processarCompra(CompraEvent compraEvent) {
        System.out.println("📩 Evento recebido no jogo-service: " + compraEvent);

        for (Long jogoId : compraEvent.getJogosIds()) {
            var jogo = jogoService.buscarPorId(jogoId);
            if (jogo != null) {
                System.out.println("🎮 Jogo " + jogo.getTitulo() + " foi comprado.");
            }
        }
    }
}

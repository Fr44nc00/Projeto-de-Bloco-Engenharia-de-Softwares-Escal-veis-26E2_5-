package com.francobbs.lojavideogames.application;

import com.francobbs.lojavideogames.domain.Compra;
import com.francobbs.lojavideogames.infrastructure.CompraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CompraServiceTest {

    @Autowired
    private CompraRepository compraRepository;

    @BeforeEach
    void limparBanco() {
        compraRepository.deleteAll();
    }

    @Test
    void deveBuscarComprasPorValorMinimo() {

        Compra compraBaixa = new Compra();
        compraBaixa.setDataHora(LocalDateTime.now());
        compraBaixa.setValorTotal(100.0);

        Compra compraAlta = new Compra();
        compraAlta.setDataHora(LocalDateTime.now());
        compraAlta.setValorTotal(500.0);

        compraRepository.save(compraBaixa);
        compraRepository.save(compraAlta);

        List<Compra> compras =
                compraRepository.findByValorTotalGreaterThanEqual(300.0);

        assertThat(compras).hasSize(1);
        assertThat(compras.get(0).getValorTotal()).isEqualTo(500.0);
    }

    @Test
    void deveBuscarComprasPorPeriodo() {

        Compra compra = new Compra();
        compra.setDataHora(LocalDateTime.now());
        compra.setValorTotal(250.0);

        compraRepository.save(compra);

        List<Compra> compras =
                compraRepository.findByDataHoraBetween(
                        LocalDateTime.now().minusDays(1),
                        LocalDateTime.now().plusDays(1)
                );

        assertThat(compras).isNotEmpty();
    }
}
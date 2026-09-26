package com.francobbs.usuarioservice.domain;

import java.time.LocalDateTime;
import java.util.List;

public class CompraEvent {
    private Long id;
    private Long usuarioId;
    private List<Long> jogosIds;
    private Double valorTotal;
    private LocalDateTime dataHora;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<Long> getJogosIds() {
        return jogosIds;
    }

    public void setJogosIds(List<Long> jogosIds) {
        this.jogosIds = jogosIds;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}

package com.francobbs.lojavideogames.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título do jogo é obrigatório")
    private String titulo;

    @NotBlank(message = "A plataforma é obrigatória")
    private String plataforma;

    @NotNull(message = "O preço deve ser informado")
    @Min(value = 0, message = "O preço não pode ser negativo")
    private Double preco;

    public Jogo() {}

    public Jogo(Long id, String titulo, String plataforma, Double preco) {
        this.id = id;
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}

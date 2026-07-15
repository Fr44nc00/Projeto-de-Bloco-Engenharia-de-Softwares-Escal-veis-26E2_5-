package com.francobbs.lojavideogames.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

import org.hibernate.envers.Audited;

@Audited
@Entity
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título do jogo é obrigatório")
    private String titulo;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "A plataforma é obrigatória")
    private Plataforma plataforma;

    @NotNull(message = "O preço deve ser informado")
    @Min(value = 0, message = "O preço não pode ser negativo")
    private Double preco;

    @ManyToMany(mappedBy = "jogos", fetch = FetchType.LAZY)
    private List<Compra> compras;

    public Jogo() {}

    public Jogo(Long id, String titulo, Plataforma plataforma, Double preco) {
        this.id = id;
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.preco = preco;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Plataforma getPlataforma() { return plataforma; }
    public void setPlataforma(Plataforma plataforma) { this.plataforma = plataforma; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }

    public List<Compra> getCompras() { return compras; }
    public void setCompras(List<Compra> compras) { this.compras = compras; }
}

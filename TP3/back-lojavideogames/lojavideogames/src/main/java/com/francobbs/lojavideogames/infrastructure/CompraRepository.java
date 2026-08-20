package com.francobbs.lojavideogames.infrastructure;

import com.francobbs.lojavideogames.domain.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    List<Compra> findByUsuarioId(Long usuarioId);

    List<Compra> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);

    List<Compra> findByValorTotalGreaterThanEqual(Double valor);
}


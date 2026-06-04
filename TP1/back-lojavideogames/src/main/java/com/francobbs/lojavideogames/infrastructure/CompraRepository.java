package com.francobbs.lojavideogames.infrastructure;

import com.francobbs.lojavideogames.domain.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
}

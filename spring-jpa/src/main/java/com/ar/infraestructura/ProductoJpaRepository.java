package com.ar.infraestructura;

import com.ar.dominio.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoJpaRepository extends JpaRepository<Producto, Long> {
}

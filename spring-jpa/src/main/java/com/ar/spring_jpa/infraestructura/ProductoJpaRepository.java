package com.ar.spring_jpa.infraestructura;

import com.ar.spring_jpa.dominio.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoJpaRepository extends JpaRepository<Producto, Long> {
}

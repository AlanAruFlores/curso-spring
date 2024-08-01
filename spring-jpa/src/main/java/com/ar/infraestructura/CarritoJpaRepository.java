package com.ar.infraestructura;

import com.ar.dominio.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoJpaRepository extends JpaRepository<Carrito, Long> {
}

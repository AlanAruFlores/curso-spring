package com.ar.spring_jpa.infraestructura;

import com.ar.spring_jpa.dominio.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoJpaRepository extends JpaRepository<Carrito, Long> {

}

package com.ar.spring_jpa.infraestructura;

import com.ar.spring_jpa.dominio.Carrito;
import com.ar.spring_jpa.dominio.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ProductoJpaRepository extends JpaRepository<Producto, Long> {
    Set<Producto> findAllByCarrito(Carrito carrito);
}

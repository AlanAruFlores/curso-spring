package com.ar.spring_jpa.infraestructura;

import com.ar.spring_jpa.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UsuarioJpaRepository extends JpaRepository<Usuario, Long> {
}

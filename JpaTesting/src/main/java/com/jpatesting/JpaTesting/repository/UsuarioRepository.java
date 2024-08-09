package com.jpatesting.JpaTesting.repository;

import com.jpatesting.JpaTesting.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}

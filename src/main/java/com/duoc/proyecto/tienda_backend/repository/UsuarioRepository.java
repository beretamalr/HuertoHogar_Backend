package com.duoc.proyecto.tienda_backend.repository;

import com.duoc.proyecto.tienda_backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email); // útil para login
}
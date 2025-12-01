package com.duoc.proyecto.tienda_backend.repository;

import com.duoc.proyecto.tienda_backend.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {}
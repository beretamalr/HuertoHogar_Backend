package com.duoc.proyecto.tienda_backend.repository;

import com.duoc.proyecto.tienda_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByNombre(String nombre);
    // Métodos CRUD heredados
}
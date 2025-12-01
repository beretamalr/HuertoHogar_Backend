package com.duoc.proyecto.tienda_backend.service;

import com.duoc.proyecto.tienda_backend.model.Product;
import com.duoc.proyecto.tienda_backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        if (product.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser positivo.");
        }
        if (product.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        // Validar nombre único
        if (productRepository.findByNombre(product.getNombre()).isPresent()) {
            throw new RuntimeException("Ya existe un producto con ese nombre");
        }
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}

package com.duoc.proyecto.tienda_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.duoc.proyecto.tienda_backend.model.Product;
import com.duoc.proyecto.tienda_backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Productos", description = "Operaciones CRUD para productos")
@RestController
@RequestMapping("/api/productos")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Operation(summary = "Obtener todos los productos", description = "Devuelve una lista de todos los productos.", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Operation(summary = "Obtener producto por ID", description = "Devuelve un producto según su ID.", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Operation(summary = "Crear producto", description = "Crea un nuevo producto.", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public Product create(@RequestBody Product producto) {
        return productRepository.save(producto);
    }

    @Operation(summary = "Actualizar producto", description = "Actualiza un producto existente por ID.", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product producto) {
        producto.setId(id);
        return productRepository.save(producto);
    }

    @Operation(summary = "Eliminar producto", description = "Elimina un producto por ID.", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}
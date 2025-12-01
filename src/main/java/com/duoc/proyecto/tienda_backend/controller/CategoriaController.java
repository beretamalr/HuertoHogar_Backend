package com.duoc.proyecto.tienda_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.duoc.proyecto.tienda_backend.model.Categoria;
import com.duoc.proyecto.tienda_backend.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Categorías", description = "Operaciones CRUD para categorías")
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Operation(summary = "Obtener todas las categorías", description = "Devuelve una lista de todas las categorías.", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public List<Categoria> getAll() {
        return categoriaRepository.findAll();
    }

    @Operation(summary = "Crear categoría", description = "Crea una nueva categoría.", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public Categoria create(@RequestBody Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
}
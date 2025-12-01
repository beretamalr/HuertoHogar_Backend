package com.duoc.proyecto.tienda_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.duoc.proyecto.tienda_backend.model.Boleta;
import com.duoc.proyecto.tienda_backend.repository.BoletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Boletas", description = "Operaciones CRUD para boletas")
@RestController
@RequestMapping("/api/boletas")
public class BoletaController {
    @Autowired
    private BoletaRepository boletaRepository;

    @Operation(summary = "Obtener todas las boletas", description = "Devuelve una lista de todas las boletas.", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public List<Boleta> getAll() {
        return boletaRepository.findAll();
    }

    @Operation(summary = "Crear boleta", description = "Crea una nueva boleta.", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public Boleta create(@RequestBody Boleta boleta) {
        return boletaRepository.save(boleta);
    }
}
package com.duoc.proyecto.tienda_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.duoc.proyecto.tienda_backend.model.DetalleBoleta;
import com.duoc.proyecto.tienda_backend.repository.DetalleBoletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Detalles de Boleta", description = "Operaciones CRUD para detalles de boleta")
@RestController
@RequestMapping("/api/detalles")
public class DetalleBoletaController {
    @Autowired
    private DetalleBoletaRepository detalleBoletaRepository;

    @Operation(summary = "Obtener todos los detalles", description = "Devuelve una lista de todos los detalles de boleta.", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public List<DetalleBoleta> getAll() {
        return detalleBoletaRepository.findAll();
    }

    @Operation(summary = "Crear detalle de boleta", description = "Crea un nuevo detalle de boleta.", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public DetalleBoleta create(@RequestBody DetalleBoleta detalle) {
        return detalleBoletaRepository.save(detalle);
    }
}
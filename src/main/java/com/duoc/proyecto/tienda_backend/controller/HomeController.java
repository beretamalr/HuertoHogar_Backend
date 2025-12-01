package com.duoc.proyecto.tienda_backend.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Home", description = "Endpoint de bienvenida para la API de Tienda Backend.")
public class HomeController {
    @GetMapping("/")
    @Operation(summary = "Bienvenida", description = "Muestra un mensaje de bienvenida para comprobar que el backend está activo.")
    public String home() {
        return "Bienvenido a la API de Tienda Backend";
    }
}

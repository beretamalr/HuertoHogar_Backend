package com.duoc.proyecto.tienda_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos para registro de usuario")
public class RegisterRequest {
    @Schema(description = "Nombre del usuario", example = "Juan Perez")
    private String nombre;
    @Schema(description = "Correo electrónico del usuario", example = "usuario@ejemplo.com")
    private String email;
    @Schema(description = "Contraseña del usuario", example = "123456")
    private String password;
    @Schema(description = "Rol del usuario (ADMIN, VENDEDOR, CLIENTE)", example = "CLIENTE")
    private String rol;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}

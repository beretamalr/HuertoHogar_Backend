package com.duoc.proyecto.tienda_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos para login de usuario")
public class LoginRequest {
    @Schema(description = "Correo electrónico del usuario", example = "usuario@ejemplo.com")
    private String email;
    @Schema(description = "Contraseña del usuario", example = "123456")
    private String password;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

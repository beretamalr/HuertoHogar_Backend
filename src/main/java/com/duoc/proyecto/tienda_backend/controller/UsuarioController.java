package com.duoc.proyecto.tienda_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.duoc.proyecto.tienda_backend.model.Usuario;
import com.duoc.proyecto.tienda_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuarios", description = "Operaciones CRUD para usuarios")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Operation(summary = "Obtener todos los usuarios", description = "Devuelve una lista de todos los usuarios.", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @Operation(summary = "Obtener usuario por ID", description = "Devuelve un usuario según su ID.", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{id}")
    public Usuario getById(@PathVariable Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Operation(summary = "Crear usuario", description = "Crea un nuevo usuario.", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public Usuario create(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Operation(summary = "Actualizar usuario", description = "Actualiza un usuario existente por ID.", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/{id}")
    public Usuario update(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }

    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario por ID.", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
}
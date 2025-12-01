
package com.duoc.proyecto.tienda_backend.controller;

import com.duoc.proyecto.tienda_backend.dto.RegisterRequest;
import com.duoc.proyecto.tienda_backend.dto.LoginRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.duoc.proyecto.tienda_backend.model.Usuario;
import com.duoc.proyecto.tienda_backend.repository.UsuarioRepository;
import com.duoc.proyecto.tienda_backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "Operaciones de autenticación y registro")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    @Operation(summary = "Iniciar sesión", description = "Permite a un usuario autenticarse y obtener un token JWT.")
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        Map<String, Object> response = new HashMap<>();
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isPresent() && passwordEncoder.matches(password, usuarioOpt.get().getPassword())) {
            Usuario usuario = usuarioOpt.get();
            String token = jwtUtil.generateToken(usuario.getEmail(), usuario.getRol());
            response.put("token", token);
            response.put("rol", usuario.getRol());
            response.put("nombre", usuario.getNombre());
        } else {
            response.put("error", "Credenciales inválidas");
        }
        return response;
    }

    @Operation(summary = "Registrar usuario", description = "Permite registrar un nuevo usuario en el sistema.")
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody RegisterRequest registerRequest) {
        Map<String, Object> response = new HashMap<>();
        String nombre = registerRequest.getNombre();
        String email = registerRequest.getEmail();
        String password = registerRequest.getPassword();
        String rol = registerRequest.getRol() != null ? registerRequest.getRol() : "CLIENTE";

        if (usuarioRepository.findByEmail(email).isPresent()) {
            response.put("error", "El correo ya está registrado");
            return response;
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setPassword(passwordEncoder.encode(password));
        nuevoUsuario.setRol(rol);
        usuarioRepository.save(nuevoUsuario);

        response.put("mensaje", "Usuario registrado exitosamente");
        response.put("nombre", nombre);
        response.put("email", email);
        response.put("rol", rol);
        return response;
    }
}

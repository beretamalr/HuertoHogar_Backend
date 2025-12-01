package com.duoc.proyecto.tienda_backend.service;

import com.duoc.proyecto.tienda_backend.model.Usuario;
import com.duoc.proyecto.tienda_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + usuario.getRol());
        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(), usuario.getPassword(), Collections.singleton(authority)
        );
    }
}

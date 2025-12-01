package com.duoc.proyecto.tienda_backend.controller;

import com.duoc.proyecto.tienda_backend.dto.PagoSimuladoRequest;
import com.duoc.proyecto.tienda_backend.model.Boleta;
import com.duoc.proyecto.tienda_backend.model.DetalleBoleta;
import com.duoc.proyecto.tienda_backend.model.Product;
import com.duoc.proyecto.tienda_backend.model.Usuario;
import com.duoc.proyecto.tienda_backend.repository.BoletaRepository;
import com.duoc.proyecto.tienda_backend.repository.DetalleBoletaRepository;
import com.duoc.proyecto.tienda_backend.repository.ProductRepository;
import com.duoc.proyecto.tienda_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/pago")
public class PagoSimuladoController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BoletaRepository boletaRepository;
    @Autowired
    private DetalleBoletaRepository detalleBoletaRepository;

    @PostMapping("/simular")
    public Map<String, Object> simularPago(@RequestBody PagoSimuladoRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        Map<String, Object> response = new HashMap<>();
        if (userDetails == null) {
            response.put("error", "No autenticado");
            return response;
        }
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(userDetails.getUsername());
        if (usuarioOpt.isEmpty()) {
            response.put("error", "Usuario no encontrado");
            return response;
        }
        Usuario usuario = usuarioOpt.get();
        // Validación simple de tarjeta (solo formato, no real)
        if (request.getNumeroTarjeta() == null || request.getNumeroTarjeta().length() < 12) {
            response.put("error", "Número de tarjeta inválido");
            return response;
        }
        // Crear boleta
        Boleta boleta = new Boleta();
        boleta.setUsuario(usuario);
        boleta.setFecha(LocalDateTime.now());
        boleta = boletaRepository.save(boleta);
        List<DetalleBoleta> detalles = new ArrayList<>();
        for (PagoSimuladoRequest.ProductoCompra pc : request.getProductos()) {
            Optional<Product> prodOpt = productRepository.findById(pc.getId());
            if (prodOpt.isEmpty()) continue;
            Product prod = prodOpt.get();
            DetalleBoleta detalle = new DetalleBoleta();
            detalle.setBoleta(boleta);
            detalle.setProducto(prod);
            detalle.setCantidad(pc.getCantidad());
            detalle.setPrecioUnitario(prod.getPrecio());
            detalles.add(detalle);
        }
        detalleBoletaRepository.saveAll(detalles);
        response.put("mensaje", "Pago simulado exitoso");
        response.put("boletaId", boleta.getId());
        return response;
    }
}

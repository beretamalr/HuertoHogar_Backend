package com.duoc.proyecto.tienda_backend.dto;

import java.util.List;

public class PagoSimuladoRequest {
    private String numeroTarjeta;
    private String nombreTitular;
    private String fechaExpiracion;
    private String cvv;
    private List<ProductoCompra> productos;

    public String getNumeroTarjeta() { return numeroTarjeta; }
    public void setNumeroTarjeta(String numeroTarjeta) { this.numeroTarjeta = numeroTarjeta; }
    public String getNombreTitular() { return nombreTitular; }
    public void setNombreTitular(String nombreTitular) { this.nombreTitular = nombreTitular; }
    public String getFechaExpiracion() { return fechaExpiracion; }
    public void setFechaExpiracion(String fechaExpiracion) { this.fechaExpiracion = fechaExpiracion; }
    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }
    public List<ProductoCompra> getProductos() { return productos; }
    public void setProductos(List<ProductoCompra> productos) { this.productos = productos; }

    public static class ProductoCompra {
        private Long id;
        private int cantidad;
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public int getCantidad() { return cantidad; }
        public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    }
}

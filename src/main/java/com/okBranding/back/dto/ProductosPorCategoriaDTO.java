package com.okBranding.back.dto;

public class ProductosPorCategoriaDTO {

    private String categoria;
    private long cantidad;

    public ProductosPorCategoriaDTO(String categoria, long cantidad) {
        this.categoria = categoria;
        this.cantidad = cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }
}

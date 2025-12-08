package com.okBranding.back.dto;

public class EstadisticasResumenDTO {

    private long usuarios;
    private long productos;
    private long productosActivos;
    private long productosInactivos;
    private long cotizaciones;
    private long cotizacionesPendientes;

    // Getters y setters
    public long getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(long usuarios) {
        this.usuarios = usuarios;
    }

    public long getProductos() {
        return productos;
    }

    public void setProductos(long productos) {
        this.productos = productos;
    }

    public long getProductosActivos() {
        return productosActivos;
    }

    public void setProductosActivos(long productosActivos) {
        this.productosActivos = productosActivos;
    }

    public long getProductosInactivos() {
        return productosInactivos;
    }

    public void setProductosInactivos(long productosInactivos) {
        this.productosInactivos = productosInactivos;
    }

    public long getCotizaciones() {
        return cotizaciones;
    }

    public void setCotizaciones(long cotizaciones) {
        this.cotizaciones = cotizaciones;
    }

    public long getCotizacionesPendientes() {
        return cotizacionesPendientes;
    }

    public void setCotizacionesPendientes(long cotizacionesPendientes) {
        this.cotizacionesPendientes = cotizacionesPendientes;
    }
}

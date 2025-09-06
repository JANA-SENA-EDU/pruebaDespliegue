package com.okBranding.back.dto;

public class EstadoProductoDTO {

    private Integer idEstadoProducto;
    private String nombreEstado;

    public EstadoProductoDTO(Integer idEstadoProducto, String nombreEstado) {
        this.idEstadoProducto = idEstadoProducto;
        this.nombreEstado = nombreEstado;
    }

    public Integer getIdEstadoProducto() {
        return idEstadoProducto;
    }

    public void setIdEstadoProducto(Integer idEstadoProducto) {
        this.idEstadoProducto = idEstadoProducto;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
}

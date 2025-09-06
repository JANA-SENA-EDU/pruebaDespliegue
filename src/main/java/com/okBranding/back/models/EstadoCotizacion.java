package com.okBranding.back.models;

import jakarta.persistence.*;

@Entity
@Table(name = "estado_cotizacion")
public class EstadoCotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_cotizacion")
    private Integer idEstadoCotizacion;

    @Column(name = "nombre_estado_cotizacion", length = 50, nullable = false)
    private String nombreEstadoCotizacion;

    public Integer getIdEstadoCotizacion() {
        return idEstadoCotizacion;
    }

    public void setIdEstadoCotizacion(Integer idEstadoCotizacion) {
        this.idEstadoCotizacion = idEstadoCotizacion;
    }

    public String getNombreEstadoCotizacion() {
        return nombreEstadoCotizacion;
    }

    public void setNombreEstadoCotizacion(String nombreEstadoCotizacion) {
        this.nombreEstadoCotizacion = nombreEstadoCotizacion;
    }
}
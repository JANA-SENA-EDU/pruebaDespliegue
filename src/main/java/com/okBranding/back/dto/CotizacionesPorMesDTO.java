package com.okBranding.back.dto;

public class CotizacionesPorMesDTO {

    private String mes;
    private long cantidad;

    public CotizacionesPorMesDTO(String mes, long cantidad) {
        this.mes = mes;
        this.cantidad = cantidad;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }
}

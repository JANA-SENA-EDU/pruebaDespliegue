package com.okBranding.back.dto;

public class ColorDTO {
    private Integer idColor;
    private String codigoColor;

    public ColorDTO() {}

    public ColorDTO(Integer idColor, String codigoColor) {
        this.idColor = idColor;
        this.codigoColor = codigoColor;
    }

    public Integer getIdColor() {
        return idColor;
    }

    public void setIdColor(Integer idColor) {
        this.idColor = idColor;
    }

    public String getCodigoColor() {
        return codigoColor;
    }

    public void setCodigoColor(String codigoColor) {
        this.codigoColor = codigoColor;
    }
}

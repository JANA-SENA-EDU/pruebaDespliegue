package com.okBranding.back.dto;

public class ImagenDTO {
    private String urlImagen;

    public ImagenDTO() {}

    public ImagenDTO(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}

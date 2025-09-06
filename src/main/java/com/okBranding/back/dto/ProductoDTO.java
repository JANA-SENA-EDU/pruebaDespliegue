package com.okBranding.back.dto;
import java.util.List;

public class ProductoDTO {
    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private String dimensiones;
    private Integer idEstadoProducto;
    private Integer idCategoria;

    private List<ImagenDTO> imagenes;
    private List<ColorDTO> colores;

    public ProductoDTO() {}

    public ProductoDTO(String nombre, String descripcion, String dimensiones,
                       Integer idEstadoProducto, Integer idCategoria,
                       List<ImagenDTO> imagenes, List<ColorDTO> colores) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dimensiones = dimensiones;
        this.idEstadoProducto = idEstadoProducto;
        this.idCategoria = idCategoria;
        this.imagenes = imagenes;
        this.colores = colores;
    }

    // Getters y setters

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public Integer getIdEstadoProducto() {
        return idEstadoProducto;
    }

    public void setIdEstadoProducto(Integer idEstadoProducto) {
        this.idEstadoProducto = idEstadoProducto;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public List<ImagenDTO> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<ImagenDTO> imagenes) {
        this.imagenes = imagenes;
    }

    public List<ColorDTO> getColores() {
        return colores;
    }

    public void setColores(List<ColorDTO> colores) {
        this.colores = colores;
    }
}

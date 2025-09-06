package com.okBranding.back.service;

import com.okBranding.back.dto.EstadoProductoDTO;
import com.okBranding.back.dto.ProductoDTO;

import java.util.List;
import java.util.Map;

public interface ProductoService {
    Map<String, Object> registrarProducto(ProductoDTO dto);
    List<ProductoDTO> listarProductos();
    Map<String, Object> actualizarProducto(ProductoDTO dto);
    Map<String, Object> eliminarProducto(Integer idProducto);
    List<ProductoDTO> listarPorCategoria(Integer idCategoria);

    // Agrega esta línea:
    ProductoDTO obtenerProductoPorId(Integer idProducto);
}
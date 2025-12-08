package com.okBranding.back.service;

import com.okBranding.back.dto.CotizacionesPorMesDTO;
import com.okBranding.back.dto.ProductosPorCategoriaDTO;
import com.okBranding.back.dto.ProductosPorEstadoDTO;

import java.util.List;

public interface EstadisticasService {

    long contarUsuarios();

    long contarProductos();

    long contarProductosActivos();

    long contarProductosInactivos();

    long contarCotizaciones();

    long contarCotizacionesPendientes();

    List<CotizacionesPorMesDTO> obtenerCotizacionesPorMes();

    List<ProductosPorCategoriaDTO> obtenerProductosPorCategoria();

    List<ProductosPorEstadoDTO> obtenerProductosPorEstado();

    byte[] generarReporteProductosProfesionalPDF() throws Exception;
}

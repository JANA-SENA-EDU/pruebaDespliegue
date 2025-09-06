package com.okBranding.back.service;

import com.okBranding.back.dto.EstadoProductoDTO;
import java.util.List;
import java.util.Map;

public interface EstadoProductoService {

    Map<String, Object> registrarEstadoProducto(EstadoProductoDTO dto);
    List<EstadoProductoDTO> listarEstadosProducto();
    Map<String, Object> actualizarEstadoProducto(EstadoProductoDTO dto);
    Map<String, Object> eliminarEstadoProducto(Integer id);
}


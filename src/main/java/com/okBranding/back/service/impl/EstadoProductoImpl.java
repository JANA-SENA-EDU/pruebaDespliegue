package com.okBranding.back.service.impl;

import com.okBranding.back.dto.EstadoProductoDTO;
import com.okBranding.back.models.EstadoProducto;
import com.okBranding.back.repository.EstadoProductoRepository;
import com.okBranding.back.service.EstadoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EstadoProductoImpl implements EstadoProductoService{

    @Autowired
    private EstadoProductoRepository estadoProductoRepository;

    @Override
    public Map<String, Object> registrarEstadoProducto(EstadoProductoDTO dto) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            EstadoProducto estado = new EstadoProducto();
            estado.setNombreEstado(dto.getNombreEstado());

            EstadoProducto guardado = estadoProductoRepository.save(estado);

            EstadoProductoDTO responseDTO = new EstadoProductoDTO(
                    guardado.getIdEstadoProducto(), guardado.getNombreEstado());

            respuesta.put("exitoso", true);
            respuesta.put("message", "Estado registrado exitosamente");
            respuesta.put("data", responseDTO);
        } catch (Exception e) {
            respuesta.put("exitoso", false);
            respuesta.put("message", "Error al registrar estado: " + e.getMessage());
        }
        return respuesta;
    }

    @Override
    public List<EstadoProductoDTO> listarEstadosProducto() {
        return estadoProductoRepository.findAll().stream()
                .map(e -> new EstadoProductoDTO(e.getIdEstadoProducto(), e.getNombreEstado()))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> actualizarEstadoProducto(EstadoProductoDTO dto) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            EstadoProducto estado = estadoProductoRepository.findById(dto.getIdEstadoProducto())
                    .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

            estado.setNombreEstado(dto.getNombreEstado());
            estadoProductoRepository.save(estado);

            respuesta.put("exitoso", true);
            respuesta.put("message", "Estado actualizado correctamente");
        } catch (Exception e) {
            respuesta.put("exitoso", false);
            respuesta.put("message", "Error al actualizar estado: " + e.getMessage());
        }
        return respuesta;
    }

    @Override
    public Map<String, Object> eliminarEstadoProducto(Integer id) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            EstadoProducto estado = estadoProductoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
            estadoProductoRepository.delete(estado);

            respuesta.put("exitoso", true);
            respuesta.put("message", "Estado eliminado correctamente");
        } catch (Exception e) {
            respuesta.put("exitoso", false);
            respuesta.put("message", "Error al eliminar estado: " + e.getMessage());
        }
        return respuesta;
    }
}

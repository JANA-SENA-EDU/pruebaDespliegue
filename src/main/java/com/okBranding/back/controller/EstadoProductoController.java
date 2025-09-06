package com.okBranding.back.controller;

import com.okBranding.back.dto.EstadoProductoDTO;
import com.okBranding.back.service.EstadoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/okBranding/estado-producto")
@CrossOrigin(origins = "*")
public class EstadoProductoController {

    @Autowired
    private EstadoProductoService estadoProductoService;

    /**
     * Endpoint para registrar un nuevo estado de producto
     */
        @PostMapping("/registrar")
    public ResponseEntity<Map<String, Object>> registrarEstadoProducto(@RequestBody EstadoProductoDTO dto) {
        Map<String, Object> respuesta = estadoProductoService.registrarEstadoProducto(dto);
        return (boolean) respuesta.get("exitoso")
                ? ResponseEntity.status(HttpStatus.CREATED).body(respuesta)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    /**
     * Endpoint para listar todos los estados de producto
     */
    @GetMapping("/listar")
    public ResponseEntity<List<EstadoProductoDTO>> listarEstadosProducto() {
        List<EstadoProductoDTO> estados = estadoProductoService.listarEstadosProducto();
        return ResponseEntity.ok(estados);
    }

    /**
     * Endpoint para actualizar un estado de producto
     */
    @PutMapping("/actualizar")
    public ResponseEntity<Map<String, Object>> actualizarEstadoProducto(@RequestBody EstadoProductoDTO dto) {
        Map<String, Object> respuesta = estadoProductoService.actualizarEstadoProducto(dto);
        return (boolean) respuesta.get("exitoso")
                ? ResponseEntity.ok(respuesta)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    /**
     * Endpoint para eliminar un estado de producto
     */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Object>> eliminarEstadoProducto(@PathVariable Integer id) {
        Map<String, Object> respuesta = estadoProductoService.eliminarEstadoProducto(id);
        return (boolean) respuesta.get("exitoso")
                ? ResponseEntity.ok(respuesta)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

}

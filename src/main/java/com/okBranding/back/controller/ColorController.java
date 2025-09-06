package com.okBranding.back.controller;

import com.okBranding.back.models.Color;
import com.okBranding.back.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/okBranding/colores")
@CrossOrigin(origins = "*")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @PostMapping("/registrar")
    public ResponseEntity<Map<String, Object>> crearColor(@RequestBody Color color) {
        Map<String, Object> respuesta = colorService.crearColor(color);
        return (boolean) respuesta.get("exitoso")
                ? ResponseEntity.status(HttpStatus.CREATED).body(respuesta)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    @PutMapping("/actualizar/{idColor}")
    public ResponseEntity<Map<String, Object>> actualizarColor(@PathVariable Integer idColor, @RequestBody Color color) {
        Map<String, Object> respuesta = colorService.actualizarColor(idColor, color);
        return (boolean) respuesta.get("exitoso")
                ? ResponseEntity.ok(respuesta)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
    }

    @DeleteMapping("/eliminar/{idColor}")
    public ResponseEntity<Map<String, Object>> eliminarColor(@PathVariable Integer idColor) {
        Map<String, Object> respuesta = colorService.eliminarColor(idColor);
        return (boolean) respuesta.get("exitoso")
                ? ResponseEntity.ok(respuesta)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Color>> listarColores() {
        return ResponseEntity.ok(colorService.listarColores());
    }
}


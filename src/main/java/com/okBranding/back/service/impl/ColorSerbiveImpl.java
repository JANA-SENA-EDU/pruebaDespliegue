package com.okBranding.back.service.impl;

import com.okBranding.back.models.Color;
import com.okBranding.back.repository.ColorRepository;
import com.okBranding.back.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class ColorSerbiveImpl implements ColorService {
    @Autowired
    private ColorRepository colorRepository;

    @Override
    public Map<String, Object> crearColor(Color color) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            colorRepository.save(color);
            respuesta.put("exitoso", true);
            respuesta.put("message", "Color registrado correctamente.");
        } catch (Exception e) {
            respuesta.put("exitoso", false);
            respuesta.put("message", "Error al registrar el color.");
        }
        return respuesta;
    }

    @Override
    public Map<String, Object> actualizarColor(Integer idColor, Color color) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Color> colorExistente = colorRepository.findById(idColor);

        if (colorExistente.isPresent()) {
            Color c = colorExistente.get();
            c.setCodigoColor(color.getCodigoColor());
            c.setNombreColor(color.getNombreColor());
            colorRepository.save(c);

            respuesta.put("exitoso", true);
            respuesta.put("message", "Color actualizado correctamente.");
        } else {
            respuesta.put("exitoso", false);
            respuesta.put("message", "Color no encontrado.");
        }

        return respuesta;
    }

    @Override
    public Map<String, Object> eliminarColor(Integer idColor) {
        Map<String, Object> respuesta = new HashMap<>();
        if (colorRepository.existsById(idColor)) {
            colorRepository.deleteById(idColor);
            respuesta.put("exitoso", true);
            respuesta.put("message", "Color eliminado correctamente.");
        } else {
            respuesta.put("exitoso", false);
            respuesta.put("message", "Color no encontrado.");
        }
        return respuesta;
    }

    @Override
    public List<Color> listarColores() {
        return colorRepository.findAll();
    }
}

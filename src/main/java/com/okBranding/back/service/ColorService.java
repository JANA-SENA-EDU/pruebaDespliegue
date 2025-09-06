package com.okBranding.back.service;

import com.okBranding.back.models.Color;

import java.util.List;
import java.util.Map;

public interface ColorService {
    Map<String, Object> crearColor(Color color);

    Map<String, Object> actualizarColor(Integer idColor, Color color);

    Map<String, Object> eliminarColor(Integer idColor);

    List<Color> listarColores();
}

package com.okBranding.back.service;

import com.okBranding.back.models.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    List<Categoria> listarCategorias();
    Categoria guardarCategoria(Categoria categoria);
    Optional<Categoria> obtenerCategoriaPorId(Integer id);
    void eliminarCategoria(Integer id);
}
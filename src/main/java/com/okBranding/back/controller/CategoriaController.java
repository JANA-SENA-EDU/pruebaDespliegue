package com.okBranding.back.controller;

import com.okBranding.back.models.Categoria;
import com.okBranding.back.dto.CategoriaDTO;
import com.okBranding.back.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/okBranding/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Helper para convertir de entidad a DTO
    private CategoriaDTO toDTO(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setIdCategoria(categoria.getIdCategoria());
        dto.setNombreCategoria(categoria.getNombreCategoria());
        dto.setDescripcion(categoria.getDescripcion());
        dto.setImagen(categoria.getImagen());
        return dto;
    }

    // Helper para convertir de DTO a entidad
    private Categoria toEntity(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(dto.getIdCategoria());
        categoria.setNombreCategoria(dto.getNombreCategoria());
        categoria.setDescripcion(dto.getDescripcion());
        categoria.setImagen(dto.getImagen());
        return categoria;
    }

    // GET: Listar todas las categorías
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() {
        List<Categoria> categorias = categoriaService.listarCategorias();
        List<CategoriaDTO> dtos = categorias.stream().map(this::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // POST: Crear una nueva categoría
    @PostMapping
    public ResponseEntity<CategoriaDTO> crearCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        Categoria categoria = toEntity(categoriaDTO);
        Categoria creada = categoriaService.guardarCategoria(categoria);
        return ResponseEntity.ok(toDTO(creada));
    }

    // PUT: Actualizar una categoría existente
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> actualizarCategoria(@PathVariable Integer id, @RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.obtenerCategoriaPorId(id)
                .map(existente -> {
                    existente.setNombreCategoria(categoriaDTO.getNombreCategoria());
                    existente.setDescripcion(categoriaDTO.getDescripcion());
                    existente.setImagen(categoriaDTO.getImagen());
                    Categoria actualizada = categoriaService.guardarCategoria(existente);
                    return ResponseEntity.ok(toDTO(actualizada));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Borrar Categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Integer id) {
        try {
            Optional<Categoria> categoriaOpt = categoriaService.obtenerCategoriaPorId(id);

            if (categoriaOpt.isPresent()) {
                categoriaService.eliminarCategoria(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("mensaje", "Categoría no encontrada"));
            }
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("mensaje", "No se puede eliminar la categoría porque está asociada a uno o más productos."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("mensaje", "Error interno del servidor"));
        }
    }
}
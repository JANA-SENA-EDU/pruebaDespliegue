package com.okBranding.back.service.impl;

import com.okBranding.back.dto.ColorDTO;
import com.okBranding.back.dto.ImagenDTO;
import com.okBranding.back.dto.ProductoDTO;
import com.okBranding.back.mapper.ProductoMapper;
import com.okBranding.back.models.*;
import com.okBranding.back.repository.CategoriaRepository;
import com.okBranding.back.repository.ColorRepository;
import com.okBranding.back.repository.EstadoProductoRepository;
import com.okBranding.back.repository.ProductoRepository;
import com.okBranding.back.service.ProductoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private EstadoProductoRepository estadoProductoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoMapper productoMapper;

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public Map<String, Object> registrarProducto(ProductoDTO dto) {
        Map<String, Object> respuesta = new HashMap<>();

        try {
            // 1. Mapeo simple usando el mapper
            Producto producto = productoMapper.toEntity(dto);

            // 2. Asignación de entidades relacionadas (categoría y estado)
            Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            EstadoProducto estado = estadoProductoRepository.findById(dto.getIdEstadoProducto())
                    .orElseThrow(() -> new RuntimeException("Estado del producto no encontrado"));

            producto.setCategoria(categoria);
            producto.setEstadoProducto(estado);

            // 3. Mapeo manual de imágenes
            if (dto.getImagenes() != null && !dto.getImagenes().isEmpty()) {
                List<ImagenProducto> imagenes = dto.getImagenes().stream().map(imgDto -> {
                    ImagenProducto imagen = new ImagenProducto();
                    imagen.setUrlImagen(imgDto.getUrlImagen());
                    imagen.setProducto(producto);
                    return imagen;
                }).toList();
                producto.setImagenes(imagenes);
            }

            // 4. Mapeo manual de colores
            if (dto.getColores() != null && !dto.getColores().isEmpty()) {
                List<ProductoColor> colores = dto.getColores().stream().map(colorDto -> {
                    ProductoColor productoColor = new ProductoColor();
                    Color color = colorRepository.findById(colorDto.getIdColor())
                            .orElseThrow(() -> new RuntimeException("Color no encontrado con ID: " + colorDto.getIdColor()));
                    productoColor.setColor(color);
                    productoColor.setProducto(producto);
                    return productoColor;
                }).toList();
                producto.setColores(colores);
            }

            // 5. Guardar producto completo (producto + imágenes + colores)
            productoRepository.save(producto);

            respuesta.put("exitoso", true);
            respuesta.put("message", "Producto registrado correctamente");

        } catch (Exception e) {
            respuesta.put("exitoso", false);
            respuesta.put("message", "Error al registrar producto: " + e.getMessage());
        }

        return respuesta;
    }

    @Override
    public List<ProductoDTO> listarProductos() {
        List<Producto> productos = productoRepository.findAll();

        return productos.stream().map(producto -> {
            ProductoDTO dto = productoMapper.toDto(producto);

            // Mapear imágenes
            if (producto.getImagenes() != null) {
                List<ImagenDTO> imagenesDto = producto.getImagenes().stream().map(imagen -> {
                    ImagenDTO imgDto = new ImagenDTO();
                    imgDto.setUrlImagen(imagen.getUrlImagen());
                    return imgDto;
                }).toList();
                dto.setImagenes(imagenesDto);
            }

            // Mapear colores
            if (producto.getColores() != null) {
                List<ColorDTO> coloresDto = producto.getColores().stream().map(pc -> {
                    ColorDTO colorDto = new ColorDTO();
                    colorDto.setIdColor(pc.getColor().getIdColor());
                    colorDto.setCodigoColor(pc.getColor().getCodigoColor());
                    return colorDto;
                }).toList();
                dto.setColores(coloresDto);
            }

            return dto;
        }).toList();
    }

    @Override
    @Transactional
    public Map<String, Object> actualizarProducto(ProductoDTO dto) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            Producto producto = productoRepository.findById(dto.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            producto.setNombre(dto.getNombre());
            producto.setDescripcion(dto.getDescripcion());
            producto.setDimensiones(dto.getDimensiones());

            // Relación categoría y estado
            Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            EstadoProducto estado = estadoProductoRepository.findById(dto.getIdEstadoProducto())
                    .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

            producto.setCategoria(categoria);
            producto.setEstadoProducto(estado);

            // ACTUALIZAR IMÁGENES
            producto.getImagenes().clear(); // elimina las anteriores
            if (dto.getImagenes() != null) {
                for (ImagenDTO imgDTO : dto.getImagenes()) {
                    ImagenProducto imagen = new ImagenProducto();
                    imagen.setUrlImagen(imgDTO.getUrlImagen());
                    imagen.setProducto(producto);
                    producto.getImagenes().add(imagen);
                }
            }

            // ACTUALIZAR COLORES
            producto.getColores().clear(); // elimina los anteriores
            if (dto.getColores() != null) {
                for (ColorDTO colorDTO : dto.getColores()) {
                    ProductoColor productoColor = new ProductoColor();
                    Color color = colorRepository.findById(colorDTO.getIdColor())
                            .orElseThrow(() -> new RuntimeException("Color no encontrado con ID: " + colorDTO.getIdColor()));
                    productoColor.setColor(color);
                    productoColor.setProducto(producto);
                    producto.getColores().add(productoColor);
                }
            }

            productoRepository.save(producto);

            respuesta.put("exitoso", true);
            respuesta.put("message", "Producto actualizado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            respuesta.put("exitoso", false);
            respuesta.put("message", "Error al actualizar producto: " + e.getMessage());
        }
        return respuesta;
    }

    @Override
    public Map<String, Object> eliminarProducto(Integer idProducto) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            Producto producto = productoRepository.findById(idProducto)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            EstadoProducto estadoInactivo = estadoProductoRepository.findById(2)
                    .orElseThrow(() -> new RuntimeException("Estado INACTIVO no encontrado"));

            producto.setEstadoProducto(estadoInactivo);
            productoRepository.save(producto);

            respuesta.put("exitoso", true);
            respuesta.put("message", "Producto marcado como inactivo correctamente");
        } catch (Exception e) {
            respuesta.put("exitoso", false);
            respuesta.put("message", "Error al eliminar producto: " + e.getMessage());
        }
        return respuesta;
    }

    @Override
    public List<ProductoDTO> listarPorCategoria(Integer idCategoria) {
        List<Producto> productos = productoRepository.findByCategoriaIdCategoria(idCategoria);

        return productos.stream().map(producto -> {
            ProductoDTO dto = productoMapper.toDto(producto);

            // Mapear imágenes
            if (producto.getImagenes() != null) {
                List<ImagenDTO> imagenesDto = producto.getImagenes().stream().map(imagen -> {
                    ImagenDTO imgDto = new ImagenDTO();
                    imgDto.setUrlImagen(imagen.getUrlImagen());
                    return imgDto;
                }).toList();
                dto.setImagenes(imagenesDto);
            }

            // Mapear colores
            if (producto.getColores() != null) {
                List<ColorDTO> coloresDto = producto.getColores().stream().map(pc -> {
                    ColorDTO colorDto = new ColorDTO();
                    colorDto.setIdColor(pc.getColor().getIdColor());
                    colorDto.setCodigoColor(pc.getColor().getCodigoColor());
                    return colorDto;
                }).toList();
                dto.setColores(coloresDto);
            }

            return dto;
        }).toList();
    }
    @Override
    public ProductoDTO obtenerProductoPorId(Integer idProducto) {
        Producto producto = productoRepository.findById(idProducto)
                .orElse(null);
        if (producto == null) return null;
        ProductoDTO dto = productoMapper.toDto(producto);

        // Mapear imágenes
        if (producto.getImagenes() != null) {
            List<ImagenDTO> imagenesDto = producto.getImagenes().stream().map(imagen -> {
                ImagenDTO imgDto = new ImagenDTO();
                imgDto.setUrlImagen(imagen.getUrlImagen());
                return imgDto;
            }).toList();
            dto.setImagenes(imagenesDto);
        }

        // Mapear colores
        if (producto.getColores() != null) {
            List<ColorDTO> coloresDto = producto.getColores().stream().map(pc -> {
                ColorDTO colorDto = new ColorDTO();
                colorDto.setIdColor(pc.getColor().getIdColor());
                colorDto.setCodigoColor(pc.getColor().getCodigoColor());
                return colorDto;
            }).toList();
            dto.setColores(coloresDto);
        }

        return dto;
    }
}

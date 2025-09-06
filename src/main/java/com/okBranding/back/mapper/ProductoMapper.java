package com.okBranding.back.mapper;

import com.okBranding.back.dto.ProductoDTO;
import com.okBranding.back.models.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    @Mapping(source = "categoria.idCategoria", target = "idCategoria")
    @Mapping(source = "estadoProducto.idEstadoProducto", target = "idEstadoProducto")
    @Mapping(target = "imagenes", ignore = true)
    @Mapping(target = "colores", ignore = true)
    ProductoDTO toDto(Producto producto);

    @Mapping(target = "categoria.idCategoria", source = "idCategoria")
    @Mapping(target = "estadoProducto.idEstadoProducto", source = "idEstadoProducto")
    @Mapping(target = "imagenes", ignore = true)
    @Mapping(target = "colores", ignore = true)
    Producto toEntity(ProductoDTO dto);

    List<ProductoDTO> toDtoList(List<Producto> productos);
}

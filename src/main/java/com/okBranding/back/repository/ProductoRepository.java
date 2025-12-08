package com.okBranding.back.repository;

import com.okBranding.back.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {
    //Metodo para listar productos por idCatergoria
    List<Producto> findByCategoriaIdCategoria(Integer idCategoria);

    long countByEstadoProducto_IdEstadoProducto(Integer idEstadoProducto);

    @Query(value = """
        SELECT c.nombre_categoria AS categoria,
               COUNT(p.id_producto) AS cantidad
        FROM producto p
        JOIN categoria c ON p.id_categoria = c.id_categoria
        GROUP BY c.nombre_categoria
        ORDER BY cantidad DESC
    """, nativeQuery = true)
    List<Object[]> contarProductosPorCategoria();

    @Query(value = """
        SELECT e.nombre_estado AS estado,
               COUNT(p.id_producto) AS cantidad
        FROM producto p
        JOIN estado_producto e ON p.id_estado_producto = e.id_estado_producto
        GROUP BY e.nombre_estado
        ORDER BY cantidad DESC
    """, nativeQuery = true)
    List<Object[]> contarProductosPorEstado();

}

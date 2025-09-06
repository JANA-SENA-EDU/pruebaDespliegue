package com.okBranding.back.repository;

import com.okBranding.back.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {
    //Metodo para listar productos por idCatergoria
    List<Producto> findByCategoriaIdCategoria(Integer idCategoria);
}

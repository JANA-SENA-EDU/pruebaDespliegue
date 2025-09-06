package com.okBranding.back.repository;

import com.okBranding.back.models.CotizacionProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CotizacionProductoRepository extends JpaRepository<CotizacionProducto, Integer> {
}
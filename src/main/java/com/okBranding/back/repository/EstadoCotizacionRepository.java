package com.okBranding.back.repository;

import com.okBranding.back.models.EstadoCotizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoCotizacionRepository extends JpaRepository<EstadoCotizacion, Integer> {
}
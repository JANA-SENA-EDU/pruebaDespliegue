package com.okBranding.back.repository;

import com.okBranding.back.models.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CotizacionRepository extends JpaRepository<Cotizacion, Integer> {
    long countByEstadoCotizacion_IdEstadoCotizacion(Integer idEstadoCotizacion);

    @Query(value = """
        SELECT DATE_FORMAT(c.fecha_solicitud, '%Y-%m') AS mes,
               COUNT(*) AS cantidad
        FROM cotizacion c
        GROUP BY DATE_FORMAT(c.fecha_solicitud, '%Y-%m')
        ORDER BY mes ASC
    """, nativeQuery = true)
    List<Object[]> contarCotizacionesPorMes();


}

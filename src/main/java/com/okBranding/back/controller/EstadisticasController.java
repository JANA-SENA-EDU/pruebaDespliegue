package com.okBranding.back.controller;

import com.okBranding.back.dto.CotizacionesPorMesDTO;
import com.okBranding.back.dto.ProductosPorCategoriaDTO;
import com.okBranding.back.dto.ProductosPorEstadoDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.okBranding.back.dto.EstadisticasResumenDTO;
import com.okBranding.back.service.EstadisticasService;

import java.util.List;

@RestController
@RequestMapping("/okBranding/estadisticas")
@CrossOrigin("*")
public class EstadisticasController {

    private final EstadisticasService estadisticasService;

    public EstadisticasController(EstadisticasService estadisticasService) {
        this.estadisticasService = estadisticasService;
    }

    @GetMapping("/resumen")
    public EstadisticasResumenDTO obtenerResumen() {

        EstadisticasResumenDTO dto = new EstadisticasResumenDTO();

        dto.setUsuarios(estadisticasService.contarUsuarios());
        dto.setProductos(estadisticasService.contarProductos());
        dto.setProductosActivos(estadisticasService.contarProductosActivos());
        dto.setProductosInactivos(estadisticasService.contarProductosInactivos());
        dto.setCotizaciones(estadisticasService.contarCotizaciones());
        dto.setCotizacionesPendientes(estadisticasService.contarCotizacionesPendientes());

        return dto;
    }

    @GetMapping("/cotizaciones-por-mes")
    public List<CotizacionesPorMesDTO> obtenerCotizacionesPorMes() {
        return estadisticasService.obtenerCotizacionesPorMes();
    }

    @GetMapping("/productos-por-categoria")
    public List<ProductosPorCategoriaDTO> obtenerProductosPorCategoria() {
        return estadisticasService.obtenerProductosPorCategoria();
    }

    @GetMapping("/productos-por-estado")
    public List<ProductosPorEstadoDTO> obtenerProductosPorEstado() {
        return estadisticasService.obtenerProductosPorEstado();
    }

    @GetMapping("/reporte-productos-pro")
    public ResponseEntity<byte[]> descargarReporteProductosPro() throws Exception {

        byte[] pdf = estadisticasService.generarReporteProductosProfesionalPDF();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "reporte_productos_profesional.pdf");

        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }

}

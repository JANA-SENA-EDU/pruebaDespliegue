package com.okBranding.back.service.impl;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.okBranding.back.dto.CotizacionesPorMesDTO;
import com.okBranding.back.dto.ProductosPorCategoriaDTO;
import com.okBranding.back.dto.ProductosPorEstadoDTO;
import com.okBranding.back.models.Producto;
import org.springframework.stereotype.Service;

import com.okBranding.back.service.EstadisticasService;
import com.okBranding.back.repository.UsuarioRepository;
import com.okBranding.back.repository.ProductoRepository;
import com.okBranding.back.repository.CotizacionRepository;
import com.okBranding.back.repository.EstadoProductoRepository;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EstadisticasServiceImpl implements EstadisticasService {

    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final CotizacionRepository cotizacionRepository;
    private final EstadoProductoRepository estadoProductoRepository;

    public EstadisticasServiceImpl(
            UsuarioRepository usuarioRepository,
            ProductoRepository productoRepository,
            CotizacionRepository cotizacionRepository,
            EstadoProductoRepository estadoProductoRepository) {

        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
        this.cotizacionRepository = cotizacionRepository;
        this.estadoProductoRepository = estadoProductoRepository;
    }

    @Override
    public long contarUsuarios() {
        return usuarioRepository.count();
    }

    @Override
    public long contarProductos() {
        return productoRepository.count();
    }

    @Override
    public long contarProductosActivos() {
        return productoRepository.countByEstadoProducto_IdEstadoProducto(1);
    }

    @Override
    public long contarProductosInactivos() {
        return productoRepository.countByEstadoProducto_IdEstadoProducto(2);
    }

    @Override
    public long contarCotizaciones() {
        return cotizacionRepository.count();
    }

    @Override
    public long contarCotizacionesPendientes() {
        return cotizacionRepository.countByEstadoCotizacion_IdEstadoCotizacion(1);
    }

    @Override
    public List<CotizacionesPorMesDTO> obtenerCotizacionesPorMes() {

        List<Object[]> resultados = cotizacionRepository.contarCotizacionesPorMes();

        List<CotizacionesPorMesDTO> lista = new ArrayList<>();

        for (Object[] fila : resultados) {
            String mes = (String) fila[0];
            Long cantidad = ((Number) fila[1]).longValue();
            lista.add(new CotizacionesPorMesDTO(mes, cantidad));
        }

        return lista;
    }

    @Override
    public List<ProductosPorCategoriaDTO> obtenerProductosPorCategoria() {

        List<Object[]> resultados = productoRepository.contarProductosPorCategoria();

        List<ProductosPorCategoriaDTO> lista = new ArrayList<>();

        for (Object[] fila : resultados) {
            String categoria = (String) fila[0];
            Long cantidad = ((Number) fila[1]).longValue();

            lista.add(new ProductosPorCategoriaDTO(categoria, cantidad));
        }

        return lista;
    }

    @Override
    public List<ProductosPorEstadoDTO> obtenerProductosPorEstado() {

        List<Object[]> resultados = productoRepository.contarProductosPorEstado();

        List<ProductosPorEstadoDTO> lista = new ArrayList<>();

        for (Object[] fila : resultados) {
            String estado = (String) fila[0];
            Long cantidad = ((Number) fila[1]).longValue();
            lista.add(new ProductosPorEstadoDTO(estado, cantidad));
        }

        return lista;
    }

    @Override
    public byte[] generarReporteProductosProfesionalPDF() throws Exception {

        List<Producto> productos = productoRepository.findAll();

        Document document = new Document(PageSize.A4, 36, 36, 36, 36);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);

        document.open();

        // ===== TÍTULO PRINCIPAL =====
        Font tituloFont = new Font(Font.HELVETICA, 20, Font.BOLD);
        Paragraph titulo = new Paragraph("Reporte de Productos - OkBranding\n\n", tituloFont);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(titulo);

        // ===== FECHA =====
        Font fechaFont = new Font(Font.HELVETICA, 12, Font.NORMAL);
        String fecha = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(new java.util.Date());
        Paragraph fechaParrafo = new Paragraph("Fecha de generación: " + fecha + "\n\n", fechaFont);
        fechaParrafo.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(fechaParrafo);

        // ===== RESUMEN DE ESTADOS =====
        document.add(new Paragraph("Resumen por estado:\n", new Font(Font.HELVETICA, 14, Font.BOLD)));

        Map<String, Long> resumenEstados = productos.stream()
                .collect(java.util.stream.Collectors.groupingBy(
                        p -> p.getEstadoProducto().getNombreEstado(),
                        java.util.stream.Collectors.counting()
                ));

        for (Map.Entry<String, Long> entry : resumenEstados.entrySet()) {
            document.add(new Paragraph(" - " + entry.getKey() + ": " + entry.getValue()));
        }

        document.add(new Paragraph("\n\n"));

        // ===== SECCIONES POR ESTADO =====
        for (Map.Entry<String, Long> entry : resumenEstados.entrySet()) {

            String estado = entry.getKey();

            // Subtítulo de la sección
            Paragraph subtitulo = new Paragraph("Productos en estado: " + estado + "\n\n",
                    new Font(Font.HELVETICA, 14, Font.BOLD));
            document.add(subtitulo);

            // Tabla
            PdfPTable tabla = new PdfPTable(3); // Nombre, Categoría, Colores
            tabla.setWidthPercentage(100);
            tabla.addCell("Nombre");
            tabla.addCell("Categoría");
            tabla.addCell("Colores");

            productos.stream()
                    .filter(p -> p.getEstadoProducto().getNombreEstado().equals(estado))
                    .forEach(p -> {

                        String colores = p.getColores() != null && !p.getColores().isEmpty()
                                ? p.getColores().stream()
                                .map(pc -> pc.getColor().getNombreColor())
                                .reduce((a, b) -> a + ", " + b)
                                .orElse("Sin colores")
                                : "Sin colores";

                        tabla.addCell(p.getNombre());
                        tabla.addCell(p.getCategoria().getNombreCategoria());
                        tabla.addCell(colores);
                    });

            document.add(tabla);
            document.add(new Paragraph("\n\n")); // Espacio entre secciones
        }

        document.close();
        return baos.toByteArray();
    }

}

package com.okBranding.back.controller;

import com.okBranding.back.dto.ProductoDTO;
import com.okBranding.back.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/okBranding/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Crear producto
    @PostMapping("/registrar")
    public ResponseEntity<Map<String, Object>> registrarProducto(@RequestBody ProductoDTO productoDTO) {
        Map<String, Object> respuesta = productoService.registrarProducto(productoDTO);
        boolean exitoso = (boolean) respuesta.get("exitoso");

        return exitoso
                ? ResponseEntity.status(HttpStatus.CREATED).body(respuesta)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    // Listar productos
    @GetMapping("/listar")
    public ResponseEntity<List<ProductoDTO>> listarProductos() {
        List<ProductoDTO> productos = productoService.listarProductos();
        return ResponseEntity.ok(productos);
    }

    // Actualizar producto
    @PutMapping("/actualizar")
    public ResponseEntity<Map<String, Object>> actualizarProducto(@RequestBody ProductoDTO productoDTO) {
        Map<String, Object> respuesta = productoService.actualizarProducto(productoDTO);
        boolean exitoso = (boolean) respuesta.get("exitoso");

        return exitoso
                ? ResponseEntity.ok(respuesta)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    // Eliminar producto
    @DeleteMapping("/eliminar/{idProducto}")
    public ResponseEntity<Map<String, Object>> eliminarProducto(@PathVariable Integer idProducto) {
        Map<String, Object> respuesta = productoService.eliminarProducto(idProducto);
        boolean exitoso = (boolean) respuesta.get("exitoso");

        return exitoso
                ? ResponseEntity.ok(respuesta)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    //Listar Productos por categoria
    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<ProductoDTO>> listarPorCategoria(@PathVariable Integer idCategoria) {
        List<ProductoDTO> productos = productoService.listarPorCategoria(idCategoria);
        return ResponseEntity.ok(productos);
    }
    // Obtener producto por ID
    @GetMapping("/{idProducto}")
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable Integer idProducto) {
        ProductoDTO producto = productoService.obtenerProductoPorId(idProducto);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

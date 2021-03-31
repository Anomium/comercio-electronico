package com.tulprueba.comercioelectronico.controller

import com.tulprueba.comercioelectronico.domain.model.Producto
import com.tulprueba.comercioelectronico.domain.model.dto.CantidadProductoDTO
import com.tulprueba.comercioelectronico.domain.model.dto.ProductoDTO
import com.tulprueba.comercioelectronico.service.CarritoProductoService
import com.tulprueba.comercioelectronico.service.ProductoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/productoCarrito")
class ProductoCarritoController(val carritoProductoService: CarritoProductoService) {

    @PostMapping("/addProducto")
    fun addProducto(@RequestBody cantidadProductoDTO: CantidadProductoDTO): ResponseEntity<List<Producto>> {

        carritoProductoService.addCarritoProducto(cantidadProductoDTO);

        return ResponseEntity.ok(emptyList())
    }

    @DeleteMapping("/delete/{nombre}")
    fun deleteProductoCarrito(@PathVariable("nombre", required = true) nombre: String): ResponseEntity<List<Producto>> {

        carritoProductoService.deleteCarritoProducto(nombre);

        return ResponseEntity.ok(emptyList())
    }

}

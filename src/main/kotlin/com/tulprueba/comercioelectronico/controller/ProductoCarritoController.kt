package com.tulprueba.comercioelectronico.controller

import com.tulprueba.comercioelectronico.domain.model.dto.CantidadProductoDTO
import com.tulprueba.comercioelectronico.domain.model.dto.ResponseCheckout
import com.tulprueba.comercioelectronico.service.CarritoProductoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/productoCarrito")
class ProductoCarritoController(val carritoProductoService: CarritoProductoService) {

    @PostMapping("/addProducto")
    fun addProducto(@RequestBody cantidadProductoDTO: CantidadProductoDTO): ResponseEntity<HttpStatus> {

        carritoProductoService.addCarritoProducto(cantidadProductoDTO);

        return ResponseEntity.ok(HttpStatus.OK)
    }

    @DeleteMapping("/delete/{nombre}")
    fun deleteProductoCarrito(@PathVariable("nombre", required = true) nombre: String): ResponseEntity<HttpStatus> {

        carritoProductoService.deleteCarritoProducto(nombre);

        return ResponseEntity.ok(HttpStatus.OK)
    }

    @PutMapping("/actualizar/{nombre}/{cantidad}")
    fun updateProductoCarrito(@PathVariable("nombre", required = true) nombre: String,
                              @PathVariable("cantidad", required = true) cantidad: Int): ResponseEntity<HttpStatus> {
        carritoProductoService.putCarritoProducto(nombre, cantidad)
        return ResponseEntity.ok(HttpStatus.OK)
    }

    @GetMapping("/checkout")
    fun checkout(): ResponseEntity<ResponseCheckout> {

        var responseCheckout: ResponseCheckout = carritoProductoService.checkout()

        return ResponseEntity.ok(responseCheckout)
    }

}

package com.tulprueba.comercioelectronico.controller

import com.tulprueba.comercioelectronico.domain.model.Carrito
import com.tulprueba.comercioelectronico.service.CarritoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/carrito")
class CarritoController(val carritoService: CarritoService) {


    @PostMapping("/guardar")
    fun saveCarrito(): ResponseEntity<HttpStatus> {

        carritoService.save();

        return ResponseEntity.ok(HttpStatus.OK)
    }

    @GetMapping("/buscarCarritos")
    fun findAll(): ResponseEntity<List<Carrito>> {

        val carrito: List<Carrito> = carritoService.findAllCarrito();

        return ResponseEntity.ok(carrito)
    }


}

package com.tulprueba.comercioelectronico.controller

import com.tulprueba.comercioelectronico.domain.model.Producto
import com.tulprueba.comercioelectronico.domain.model.dto.ProductoDTO
import com.tulprueba.comercioelectronico.service.ProductoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Stream

@RestController
@RequestMapping("/api")
class ProductoController (val productoService: ProductoService) {



    @PostMapping("/producto/guardar")
    fun saveProducto(@RequestBody productoDTO: ProductoDTO): ResponseEntity<HttpStatus>{

        productoService.save(productoDTO);

        return ResponseEntity.ok(HttpStatus.OK)
    }

    @GetMapping("/producto/buscarproducto")
    fun findAll(): ResponseEntity<List<Producto>> {

        val producto: List<Producto> = productoService.findAllProducto();

        return ResponseEntity.ok(producto)
    }

    @DeleteMapping("/producto/delete/{nombre}")
    fun deleteProducto(@PathVariable("nombre", required = true) nombre: String): ResponseEntity<HttpStatus> {

        if(nombre.isEmpty())
            ResponseEntity.badRequest()

        productoService.deleteProductoByUuid(nombre)

        return ResponseEntity.ok(HttpStatus.OK)
    }

    @PostMapping("/producto/update")
    fun updateProducto(): ResponseEntity<HttpStatus> {

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/")
    fun uuid(): List<String> {
        var asd: List<String> = listOf();

        for (num in 1..15) {
            asd += arrayListOf(UUID.randomUUID().toString())
        }

        return asd
    }
}

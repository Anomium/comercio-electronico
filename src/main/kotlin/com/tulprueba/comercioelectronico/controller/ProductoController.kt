package com.tulprueba.comercioelectronico.controller

import com.tulprueba.comercioelectronico.domain.model.Producto
import com.tulprueba.comercioelectronico.domain.model.dto.ProductoDTO
import com.tulprueba.comercioelectronico.domain.model.enums.TipoProductoEstados
import com.tulprueba.comercioelectronico.service.ProductoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigInteger
import java.util.*
import java.util.stream.Stream
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/api/producto")
class ProductoController (val productoService: ProductoService) {



    @PostMapping("/guardar")
    fun saveProducto(@RequestBody productoDTO: ProductoDTO): ResponseEntity<HttpStatus>{

        productoService.save(productoDTO);

        return ResponseEntity.ok(HttpStatus.OK)
    }

    @GetMapping("/buscarproducto")
    fun findAll(): ResponseEntity<List<Producto>> {

        val producto: List<Producto> = productoService.findAllProducto();

        return ResponseEntity.ok(producto)
    }

    @DeleteMapping("/delete/{nombre}")
    fun deleteProducto(@PathVariable("nombre", required = true) nombre: String): ResponseEntity<HttpStatus> {

        if(nombre.isEmpty())
            ResponseEntity.badRequest()

        productoService.deleteProductoByUuid(nombre)

        return ResponseEntity.ok(HttpStatus.OK)
    }

    @PostMapping("/update/{condicion}")
    fun updateProducto(
        @RequestBody productoDTO: ProductoDTO,
        @PathVariable("condicion", required = false) condicion: String): ResponseEntity<HttpStatus> {

        productoService.updateProducto(condicion, productoDTO)

        return ResponseEntity.ok(HttpStatus.OK);
    }

}

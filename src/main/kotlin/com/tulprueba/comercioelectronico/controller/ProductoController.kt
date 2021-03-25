package com.tulprueba.comercioelectronico.controller

import com.tulprueba.comercioelectronico.domain.model.Producto
import com.tulprueba.comercioelectronico.domain.model.dto.ProductoDTO
import com.tulprueba.comercioelectronico.domain.model.enums.TipoProductoEstados
import com.tulprueba.comercioelectronico.service.ProductoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.math.BigInteger
import java.util.*

@RestController
@RequestMapping("/api")
class ProductoController (val productoService: ProductoService) {



    @PostMapping("/producto/guardar")
    fun saveProducto(@RequestBody productoDTO: ProductoDTO):
            ResponseEntity<HttpStatus>{

        productoService.save(productoDTO);

        return ResponseEntity.ok(HttpStatus.OK)
    }

    @PostMapping("/producto/buscarproducto")
    fun find(): ResponseEntity<HttpStatus>{



        return ResponseEntity.ok(HttpStatus.OK)
    }

}

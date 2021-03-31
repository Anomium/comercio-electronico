package com.tulprueba.comercioelectronico.service

import com.tulprueba.comercioelectronico.domain.model.Carrito
import com.tulprueba.comercioelectronico.domain.model.dto.CarritoDTO
import java.util.*


interface CarritoService {

    fun save()

    fun findAllCarrito(): List<Carrito>

}

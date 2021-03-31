package com.tulprueba.comercioelectronico.service

import com.tulprueba.comercioelectronico.domain.model.Carrito


interface CarritoService {

    fun save()

    fun findAllCarrito(): List<Carrito>

}

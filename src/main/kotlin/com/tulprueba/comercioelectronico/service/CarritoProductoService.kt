package com.tulprueba.comercioelectronico.service

import com.tulprueba.comercioelectronico.domain.model.Carrito
import com.tulprueba.comercioelectronico.domain.model.Producto
import com.tulprueba.comercioelectronico.domain.model.dto.CantidadProductoDTO
import com.tulprueba.comercioelectronico.domain.model.dto.ResponseCheckout
import java.util.*

interface CarritoProductoService {

    fun addCarritoProducto(cantidadProductoDTO: CantidadProductoDTO)

    fun deleteCarritoProducto(nombre: String)

    fun putCarritoProducto(nombre: String, catidad: Int)

    fun checkout(): ResponseCheckout
}

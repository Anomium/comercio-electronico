package com.tulprueba.comercioelectronico.service

import com.tulprueba.comercioelectronico.domain.model.Producto
import com.tulprueba.comercioelectronico.domain.model.dto.CantidadProductoDTO
import java.util.*

interface CarritoProductoService {

    fun addCarritoProducto(cantidadProductoDTO: CantidadProductoDTO)

    fun deleteCarritoProducto(nombre: String)

}

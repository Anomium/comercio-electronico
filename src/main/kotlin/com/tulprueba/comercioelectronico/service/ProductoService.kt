package com.tulprueba.comercioelectronico.service

import com.tulprueba.comercioelectronico.domain.model.Producto
import com.tulprueba.comercioelectronico.domain.model.dto.ProductoDTO
import java.util.*


interface ProductoService {

    fun save(productoDTO: ProductoDTO)

    fun findAllProducto(): List<Producto>

    fun deleteProductoByUuid(nombre: String)

    fun updateProducto(uuid: UUID, productoDTO: ProductoDTO)
}

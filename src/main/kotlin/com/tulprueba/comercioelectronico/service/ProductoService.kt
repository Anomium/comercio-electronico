package com.tulprueba.comercioelectronico.service

import com.tulprueba.comercioelectronico.domain.model.Producto
import com.tulprueba.comercioelectronico.domain.model.dto.ProductoDTO


interface ProductoService {

    fun save(productoDTO: ProductoDTO)

    fun findAllProducto(): List<Producto>

    fun deleteProductoByUuid(nombre: String)

    fun updateProducto(condicion: String, productoDTO: ProductoDTO)
}

package com.tulprueba.comercioelectronico.service

import com.tulprueba.comercioelectronico.domain.model.Producto
import org.springframework.data.repository.CrudRepository


interface ProductoService {

    fun save(producto: Producto)

    //fun findProductoByNombre();

}

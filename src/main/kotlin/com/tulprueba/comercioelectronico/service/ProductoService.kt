package com.tulprueba.comercioelectronico.service

import com.tulprueba.comercioelectronico.domain.model.Producto
import com.tulprueba.comercioelectronico.domain.model.dto.ProductoDTO
import org.springframework.data.repository.CrudRepository
import org.springframework.web.bind.annotation.RequestBody


interface ProductoService {

    fun save(productoDTO: ProductoDTO)

    //fun findProductoByNombre();

}

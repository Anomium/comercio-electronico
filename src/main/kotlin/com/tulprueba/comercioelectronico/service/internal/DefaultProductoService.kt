package com.tulprueba.comercioelectronico.service.internal

import com.tulprueba.comercioelectronico.domain.model.Producto
import com.tulprueba.comercioelectronico.infrastructure.repositories.ProductosRepository
import com.tulprueba.comercioelectronico.service.ProductoService
import org.springframework.stereotype.Service

@Service
class DefaultProductoService(val productoRepository: ProductosRepository) : ProductoService {

    override fun save(producto: Producto) {
        productoRepository.save(producto);
    }

//    override fun findProductoByNombre() {
//        TODO("Not yet implemented")
//    }

}

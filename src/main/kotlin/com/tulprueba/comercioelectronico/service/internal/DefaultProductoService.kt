package com.tulprueba.comercioelectronico.service.internal

import com.tulprueba.comercioelectronico.domain.model.Producto
import com.tulprueba.comercioelectronico.domain.model.dto.ProductoDTO
import com.tulprueba.comercioelectronico.domain.model.enums.TipoProductoEstados
import com.tulprueba.comercioelectronico.infrastructure.repositories.ProductosRepository
import com.tulprueba.comercioelectronico.service.ProductoService
import org.springframework.stereotype.Service
import java.math.BigInteger
import java.util.*

@Service
class DefaultProductoService(val productoRepository: ProductosRepository) : ProductoService {

    override fun save(productoDTO: ProductoDTO) {
        productoRepository.save(Producto(UUID.randomUUID(),
                productoDTO.nombre,
                productoDTO.sku,
                productoDTO.descripcion,
                BigInteger.valueOf(productoDTO.precio),
                TipoProductoEstados.PENDIENTE.toString()));
    }

//    override fun findProductoByNombre() {
//        TODO("Not yet implemented")
//    }

}

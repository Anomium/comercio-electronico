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
        productoRepository.save(producto(productoDTO));
    }

    override fun findAllProducto(): List<Producto> {
        return productoRepository.findAll();
    }

    override fun deleteProductoByUuid(nombre: String) {
        productoRepository.deleteProductoByUuid(nombre);
    }

    override fun updateProducto(condicion: String, productoDTO: ProductoDTO) {
        productoRepository.updateProducto(
            productoDTO.nombre,
            productoDTO.sku,
            productoDTO.descripcion,
            BigInteger.valueOf(productoDTO.precio),
            condicion)
    }

    private fun producto(productoDTO: ProductoDTO): Producto {
        return Producto(UUID.randomUUID(),
            productoDTO.nombre,
            productoDTO.sku,
            productoDTO.descripcion,
            BigInteger.valueOf(productoDTO.precio),
            TipoProductoEstados.PENDIENTE.toString())
    }

}

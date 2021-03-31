package com.tulprueba.comercioelectronico.service.internal

import com.google.gson.Gson
import com.tulprueba.comercioelectronico.domain.model.Carrito
import com.tulprueba.comercioelectronico.domain.model.Producto
import com.tulprueba.comercioelectronico.domain.model.dto.CantidadProductoDTO
import com.tulprueba.comercioelectronico.exception.CantidadNoCoincideException
import com.tulprueba.comercioelectronico.infrastructure.repositories.CarritoProductoRepository
import com.tulprueba.comercioelectronico.infrastructure.repositories.CarritoRepository
import com.tulprueba.comercioelectronico.infrastructure.repositories.ProductosRepository
import com.tulprueba.comercioelectronico.service.CarritoProductoService
import lombok.extern.java.Log
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service
import java.math.BigInteger
import java.util.*
import java.util.function.Predicate
import java.util.logging.Logger
import java.util.stream.Stream
import kotlin.collections.ArrayList

@Service
@Slf4j
class DefaultCarritoProductoService(val carritoRepository: CarritoRepository,
                                    val carritoProductoRepository: CarritoProductoRepository,
                                    val productoRepository: ProductosRepository,
                                    val gson: Gson) : CarritoProductoService{

    override fun addCarritoProducto(cantidadProductoDTO: CantidadProductoDTO) {
        if (validatorExistCarrito()) {
            var carrito: Carrito = carritoProductoRepository.findCarritoEstadoPendiente()
            var producto: Optional<Producto> = productoRepository
                .findProductoByNombreAndCantidad(cantidadProductoDTO.nombre, cantidadProductoDTO.cantidad)
            if (producto.isPresent) {
                var prod: List<Producto> = arrayListOf(producto.get());
                prod += carrito.listaProducto;
                carritoProductoRepository.save(Carrito(carrito.uuid, prod, carrito.estado))
            } else {
                println("Revise que la cantidad digitada si exista.")
            }


        }

    }

    override fun deleteCarritoProducto(nombre: String) {
        if (validatorExistCarrito()) {
            var carrito: Carrito = carritoProductoRepository.findCarritoEstadoPendiente()

            if (carrito != null) {
                var prod: MutableList<Producto> = carrito.listaProducto as MutableList<Producto>;
                var producto: Optional<Producto> = productoRepository.findProductoByNombre(nombre)
                if(producto.isPresent) {
                    prod.remove(producto.get())
                }
                carritoProductoRepository.save(Carrito(carrito.uuid, prod, carrito.estado))
            } else {
                println("Revise que la cantidad digitada si exista.")
            }


        }
    }


    fun validatorExistCarrito(): Boolean {
        return carritoRepository.getCarritoValidator() == 1
    }

}

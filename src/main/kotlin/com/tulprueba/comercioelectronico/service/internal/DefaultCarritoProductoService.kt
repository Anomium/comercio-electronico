package com.tulprueba.comercioelectronico.service.internal

import com.google.gson.Gson
import com.tulprueba.comercioelectronico.domain.model.Carrito
import com.tulprueba.comercioelectronico.domain.model.Producto
import com.tulprueba.comercioelectronico.domain.model.dto.CantidadProductoDTO
import com.tulprueba.comercioelectronico.domain.model.dto.ResponseCheckout
import com.tulprueba.comercioelectronico.domain.model.enums.TipoCarritoEstados
import com.tulprueba.comercioelectronico.domain.model.enums.TipoProductoEstados
import com.tulprueba.comercioelectronico.infrastructure.repositories.CarritoProductoRepository
import com.tulprueba.comercioelectronico.infrastructure.repositories.CarritoRepository
import com.tulprueba.comercioelectronico.infrastructure.repositories.ProductosRepository
import com.tulprueba.comercioelectronico.service.CarritoProductoService
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service
import java.math.BigInteger
import java.util.*


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
                producto.get().cantidad = cantidadProductoDTO.cantidad
                val prod: MutableList<Producto> = arrayListOf(producto.get());
                prod += carrito.listaProducto;
                carritoProductoRepository.save(Carrito(carrito.uuid, prod, carrito.estado))
            } else {
                println("Revise que la cantidad digitada o el nombre del producto si exista.")
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

    override fun putCarritoProducto(nombre: String, catidad: Int) {
        if (validatorExistCarrito()) {
            var carrito: Carrito = carritoProductoRepository.findCarritoEstadoPendiente()

            if (carrito != null) {
                var prod: MutableList<Producto> = carrito.listaProducto as MutableList<Producto>;
                var producto: Optional<Producto> = productoRepository.findProductoByNombre(nombre)
                if(producto.isPresent) {
                    prod.add(Producto(producto.get().uuid,
                        producto.get().nombre, producto.get().sku, producto.get().descripcion,
                        producto.get().precio, producto.get().tipoProducto, producto.get().cantidad))

                }
                carritoProductoRepository.save(Carrito(carrito.uuid, prod, carrito.estado))
            } else {
                println("Revise que la cantidad digitada si exista.")
            }
        }
    }

    override fun checkout(): ResponseCheckout {
        var carrito: Carrito = carritoProductoRepository.findCarritoEstadoPendiente()
        var costoFinal: BigInteger = BigInteger.valueOf(0)

        for (num in 0 .. carrito.listaProducto.size) {
            if (carrito.listaProducto.size > num) {

                if (carrito.listaProducto.get(num)
                        .tipoProducto.equals(TipoProductoEstados.DESCUENTO.toString())) {

                    costoFinal += carrito.listaProducto.get(num).precio *
                            BigInteger.valueOf(carrito.listaProducto.get(num).cantidad.toLong()) /
                            BigInteger.valueOf(2)
                } else {
                    costoFinal += carrito.listaProducto.get(num).precio *
                            BigInteger.valueOf(carrito.listaProducto.get(num).cantidad.toLong())
                }
            }
        }

        carritoProductoRepository.updateStatusCarrito(carrito.uuid.toString())
        return ResponseCheckout(costoFinal, TipoCarritoEstados.COMPLETADO.toString());
    }

    fun validatorExistCarrito(): Boolean {
        return carritoRepository.getCarritoValidator() == 1
    }


}

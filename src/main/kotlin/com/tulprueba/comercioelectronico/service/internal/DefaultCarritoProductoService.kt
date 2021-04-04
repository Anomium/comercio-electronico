package com.tulprueba.comercioelectronico.service.internal

import com.google.gson.Gson
import com.tulprueba.comercioelectronico.domain.model.Carrito
import com.tulprueba.comercioelectronico.domain.model.Producto
import com.tulprueba.comercioelectronico.domain.model.dto.CantidadProductoDTO
import com.tulprueba.comercioelectronico.domain.model.dto.ProductoDTO
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
                val producto: Producto = producto.get();
                producto.cantidad = cantidadProductoDTO.cantidad
                val prod: List<Producto> = arrayListOf(producto);
                val gson = Gson()
                val jsonGuardar: String = gson.toJson(prod)
                var concat: String = "";
                if (!carrito.listaProducto.isNullOrEmpty()) {
                    concat = "[" + carrito.listaProducto.replace("[", "")
                        .replace("]", "") + "," +
                            jsonGuardar.replace("[", "")
                                .replace("]", "") + "]"
                } else {
                    concat = "[" + jsonGuardar
                        .replace("[", "").replace("]", "") + "]"
                }
                carritoProductoRepository.save(Carrito(carrito.uuid, concat, carrito.estado))
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
                val gson = Gson()
                val json = gson.toJson(carrito.listaProducto)
                carritoProductoRepository.save(Carrito(carrito.uuid, json, carrito.estado))
            } else {
                println("Revise que la cantidad digitada si exista.")
            }
        }
    }

    override fun putCarritoProducto(nombre: String, cantidad: Int) {
        if (validatorExistCarrito()) {
            var carrito: Carrito = carritoProductoRepository.findCarritoEstadoPendiente()

            if (carrito != null) {
                //var prod: String = carrito.listaProducto;
                val json: String = carrito.listaProducto
                val productos: Array<ProductoDTO> = gson.fromJson(
                    json,
                    Array<ProductoDTO>::class.java
                )
                var producto: Optional<Producto> = productoRepository.findProductoByNombre(nombre)
                if(producto.isPresent) {
                    for (num in 0 .. productos.size) {
                        if (productos.size > num) {
                            if (cantidad <= producto.get().cantidad) {
                                productos.get(num).cantidad = cantidad;
                            }
                        }
                    }

                }
//                val gson = Gson()
                val productoJson = gson.toJson(productos)
                carritoProductoRepository.save(Carrito(carrito.uuid, productoJson, carrito.estado))
            } else {
                println("Revise que la cantidad digitada si exista.")
            }
        }
    }

    override fun checkout(): ResponseCheckout {
        var carrito: Carrito = carritoProductoRepository.findCarritoEstadoPendiente()
        var costoFinal: BigInteger = BigInteger.valueOf(0)

        val json: String = carrito.listaProducto
        val productos: Array<ProductoDTO> = gson.fromJson(
            json,
            Array<ProductoDTO>::class.java
        )

        for (num in 0 .. productos.size) {
            if (productos.size > num) {

                if (TipoProductoEstados.DESCUENTO.toString().equals(productos.get(num).tipoProducto)) {

                    costoFinal += BigInteger.valueOf(productos.get(num).precio) *
                            BigInteger.valueOf(productos.get(num).cantidad.toLong()) /
                            BigInteger.valueOf(2)
                } else {
                    costoFinal += BigInteger.valueOf(productos.get(num).precio) *
                            BigInteger.valueOf(productos.get(num).cantidad.toLong())
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

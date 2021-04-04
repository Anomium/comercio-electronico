package com.tulprueba.comercioelectronico.infrastructure.repositories

import com.tulprueba.comercioelectronico.domain.model.Carrito
import com.tulprueba.comercioelectronico.domain.model.Producto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional
import java.math.BigInteger
import java.util.*

interface CarritoProductoRepository : JpaRepository<Carrito, String>  {

    @Query("SELECT * FROM CARRITOS WHERE ESTADO = 'PENDIENTE'", nativeQuery = true)
    fun findCarritoEstadoPendiente(): Carrito

    @Transactional
    @Modifying
    @Query(value = "UPDATE CARRITOS_LISTA_PRODUCTO SET " +
            "LISTA_PRODUCTO_UUID = ?1 " +
            "WHERE TO_CHAR(CARRITO_UUID) = TO_CHAR(?2)", nativeQuery = true)
    fun updateProducto(producto: List<Producto>, uuid: String)

    @Transactional
    @Modifying
    @Query(value = "UPDATE CARRITOS SET ESTADO = 'COMPLETADO' WHERE to_char(UUID) = ?1", nativeQuery = true)
    fun updateStatusCarrito(uuid: String)

}

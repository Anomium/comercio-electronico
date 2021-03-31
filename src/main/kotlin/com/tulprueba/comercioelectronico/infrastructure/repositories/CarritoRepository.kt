package com.tulprueba.comercioelectronico.infrastructure.repositories

import com.tulprueba.comercioelectronico.domain.model.Carrito
import com.tulprueba.comercioelectronico.domain.model.Producto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional
import java.math.BigInteger

interface CarritoRepository : JpaRepository<Carrito, String> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM PRODUCTO WHERE NOMBRE = ?1", nativeQuery = true)
    fun deleteProductoByUuid(nombre: String)

    @Query(value = "SELECT " +
            "UUID, " +
            "LISTA_PRODUCTOS_UUID AS LISTA_PRODUCTOS, " +
            "ESTADO " +
            "FROM CARRITO\n" +
            "LEFT JOIN CARRITO_LISTA_PRODUCTOS\n" +
            "ON UUID = CARRITO_UUID\n" +
            "AND ESTADO = 'PENDIENTE'", nativeQuery = true)
    fun getCarrito(): Carrito

    @Query(value = "SELECT COUNT(*) " +
            "FROM CARRITOS " +
            "WHERE ESTADO = 'PENDIENTE'", nativeQuery = true)
    fun getCarritoValidator(): Int

}

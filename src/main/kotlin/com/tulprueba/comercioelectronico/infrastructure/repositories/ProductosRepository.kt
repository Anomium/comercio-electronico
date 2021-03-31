package com.tulprueba.comercioelectronico.infrastructure.repositories

import com.tulprueba.comercioelectronico.domain.model.Producto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional
import java.math.BigInteger
import java.util.*

interface ProductosRepository : JpaRepository<Producto, String> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM PRODUCTO WHERE NOMBRE = ?1", nativeQuery = true)
    fun deleteProductoByUuid(nombre: String)

    @Transactional
    @Modifying
    @Query(value = "UPDATE PRODUCTO SET " +
            "NOMBRE = ?1, " +
            "SKU = ?2, " +
            "DESCRIPCION = ?3 ," +
            "PRECIO = ?4 " +
            "WHERE NOMBRE = ?5", nativeQuery = true)
    fun updateProducto(nombre: String, sku: String, descripcion: String, precio: BigInteger, condicion: String)

    @Query("SELECT * FROM PRODUCTO WHERE NOMBRE = ?1 AND CANTIDAD >= ?2 AND CANTIDAD <> 0", nativeQuery = true)
    fun findProductoByNombreAndCantidad(nombre: String, cantidad: Int) : Optional<Producto>
}

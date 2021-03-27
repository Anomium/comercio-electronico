package com.tulprueba.comercioelectronico.infrastructure.repositories

import com.tulprueba.comercioelectronico.domain.model.Producto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface ProductosRepository : JpaRepository<Producto, String> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM PRODUCTO WHERE NOMBRE = ?1", nativeQuery = true)
    fun deleteProductoByUuid(nombre: String)

}

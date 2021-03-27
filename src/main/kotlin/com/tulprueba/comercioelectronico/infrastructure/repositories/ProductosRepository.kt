package com.tulprueba.comercioelectronico.infrastructure.repositories

import com.tulprueba.comercioelectronico.domain.model.Producto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface ProductosRepository : JpaRepository<Producto, String> {


}

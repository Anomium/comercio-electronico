package com.tulprueba.comercioelectronico.domain.model

import com.tulprueba.comercioelectronico.domain.model.enums.TipoProductoEstados
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
//@Table(name = "PRODUCTOS")
data class Producto (

        @Id
        @Column(columnDefinition = "binary(16)")
        val uuid: UUID,

        @Column(unique = true)
        val nombre: String,

        val sku: String,

        val descripcion: String,

        val precio: BigInteger,

        val tipoProducto: String
) {
        private constructor(): this(UUID.randomUUID(), "", "", "", BigInteger.valueOf(1), "")
}

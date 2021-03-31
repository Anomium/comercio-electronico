package com.tulprueba.comercioelectronico.domain.model

import java.math.BigInteger
import java.util.*
import javax.persistence.*

@Entity
//@Table(name = "PRODUCTOS")
data class Producto(

        @Id
        @Column(columnDefinition = "binary(16)")
        val uuid: UUID,

        @Column(unique = true)
        val nombre: String,

        val sku: String,

        val descripcion: String,

        val precio: BigInteger,

        val tipoProducto: String,

        @Column(name = "CANTIDAD")
        var cantidad: Int
) {
        private constructor(): this(UUID.randomUUID(), "", "", "", BigInteger.valueOf(1), "", 1)
}

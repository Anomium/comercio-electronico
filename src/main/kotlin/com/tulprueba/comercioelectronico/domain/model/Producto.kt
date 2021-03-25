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
//        @Column(name = "UUID")
        val uuid: UUID,

//        @Column(name = "NOMBRE")
        val nombre: String,

//        @Column(name = "SKU")
        val sku: String,

//        @Column(name = "DESCRIPCION")
        val descripcion: String,

//        @Column(name = "PRECIO")
        val precio: BigInteger,

//        @Column(name = "TIPO_PRODUCTO")
        val tipoProducto: String
) {
        private constructor(): this(UUID.randomUUID(), "", "", "", BigInteger.valueOf(1), "")
}

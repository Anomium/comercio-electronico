package com.tulprueba.comercioelectronico.domain.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigInteger
import java.util.*

class ProductoDTO (

    val nombre: String,

    val sku: String,

    val descripcion: String,

    val precio: Long

){
    private constructor(): this("", "", "", 1)
}

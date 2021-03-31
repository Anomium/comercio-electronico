package com.tulprueba.comercioelectronico.domain.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

class ProductoDTO (


    @JsonProperty("nombre")
    val nombre: String,

    @JsonProperty("sku")
    val sku: String,

    @JsonProperty("descripcion")
    val descripcion: String,

    @JsonProperty("precio")
    val precio: Long,

    @JsonProperty("cantidad")
    val cantidad: Int,

    @JsonProperty("tipoProducto")
    val tipoProducto: String

){
    private constructor(): this(/*UUID.randomUUID(),*/ "", "", "", 1, 1, "")
}

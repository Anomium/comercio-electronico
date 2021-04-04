package com.tulprueba.comercioelectronico.domain.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ProductoDTO (

    @JsonProperty("uuid")
    val uuid: String?,

    @JsonProperty("nombre")
    val nombre: String,

    @JsonProperty("sku")
    val sku: String,

    @JsonProperty("descripcion")
    val descripcion: String,

    @JsonProperty("precio")
    val precio: Long,

    @JsonProperty("cantidad")
    var cantidad: Int,

    @JsonProperty("tipoProducto")
    val tipoProducto: String

){
    private constructor(): this("", "", "", "", 1, 1, "")
}

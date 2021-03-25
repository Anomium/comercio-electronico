package com.tulprueba.comercioelectronico.domain.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

class ProductoDTO (

    //@JsonProperty("nombre")
    val nombre: String,

    //@JsonProperty("sku")
    val sku: String,

    //@JsonProperty("descripcion")
    val descripcion: String,

    //@JsonProperty("precio")
    val precio: Long

)

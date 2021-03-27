package com.tulprueba.comercioelectronico.domain.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

class ProductoDTO (

    val nombre: String,

    val sku: String,

    val descripcion: String,

    val precio: Long

)

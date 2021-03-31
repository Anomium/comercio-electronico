package com.tulprueba.comercioelectronico.domain.model.dto

import java.util.*

class CantidadProductoDTO(

    val nombre: String,
    val cantidad: Int

) {
    private constructor(): this("", 1)
}

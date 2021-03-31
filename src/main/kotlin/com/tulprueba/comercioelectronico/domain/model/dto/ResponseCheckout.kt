package com.tulprueba.comercioelectronico.domain.model.dto

import java.math.BigInteger

data class ResponseCheckout(

    val costoFinal: BigInteger,
    val estadoCarrito: String

) {
    private constructor(): this(BigInteger.valueOf(1), "")
}

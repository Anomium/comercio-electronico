package com.tulprueba.comercioelectronico.domain.model

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "carritos")
data class Carrito(

        @Id
        @Column(columnDefinition = "binary(16)")
        var uuid: UUID,

        @Column(name = "LISTA_PRODUCTOS")
        var listaProducto: String,

        @Column(name = "ESTADO")
        var estado: String

){
        private constructor(): this(UUID.randomUUID(), "", "")
}

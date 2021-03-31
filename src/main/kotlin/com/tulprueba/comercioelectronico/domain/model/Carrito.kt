package com.tulprueba.comercioelectronico.domain.model

import com.tulprueba.comercioelectronico.domain.model.dto.ProductoDTO
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "carritos")
data class Carrito(

        @Id
        @Column(name = "UUID")
        var uuid: UUID,

        @Column(name = "LISTA_PRODUCTOS")
        @ElementCollection
        var listaProducto: List<Producto>,

        @Column(name = "ESTADO")
        var estado: String

){
        private constructor(): this(UUID.randomUUID(), arrayListOf(), "")
}

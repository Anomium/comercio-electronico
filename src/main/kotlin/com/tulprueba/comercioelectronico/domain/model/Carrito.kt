package com.tulprueba.comercioelectronico.domain.model

import com.tulprueba.comercioelectronico.domain.model.enums.TipoProductoEstados
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "CARRITOS")
data class Carrito(

        @Id
        @Column(name = "UUID")
        var uuid: UUID,

        @OneToMany
        @Column(name = "LISTA_PRODUCTOS")
        var listaProductos: List<Producto>,

        @Column(name = "ESTADO")
        var estado: TipoProductoEstados

)

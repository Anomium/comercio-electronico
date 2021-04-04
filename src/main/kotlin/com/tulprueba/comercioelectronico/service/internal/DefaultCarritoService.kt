package com.tulprueba.comercioelectronico.service.internal

import com.tulprueba.comercioelectronico.domain.model.Carrito
import com.tulprueba.comercioelectronico.domain.model.enums.TipoCarritoEstados
import com.tulprueba.comercioelectronico.infrastructure.repositories.CarritoRepository
import com.tulprueba.comercioelectronico.service.CarritoService
import org.springframework.stereotype.Service
import java.util.*

@Service
class DefaultCarritoService(val carritoRepository: CarritoRepository) : CarritoService {

    override fun save() {
        if (validatorExistCarrito()) {
            carritoRepository.save(
                Carrito(
                    UUID.randomUUID(),
                    "",
                    TipoCarritoEstados.PENDIENTE.toString()
                )
            )
        }
    }

    override fun findAllCarrito(): List<Carrito> {
        return carritoRepository.findAll()
    }

    fun validatorExistCarrito(): Boolean {
        return carritoRepository.getCarritoValidator() < 1
    }
}

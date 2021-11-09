package com.shelf.corecontext.domain.port.input

import com.shelf.corecontext.domain.entity.Product
import java.util.*

interface ProductUseCase {
    fun findAll(page : Int, limit : Int, direction : String?): List<Product>
    fun findByBarCode(barCode : String): Optional<Product>
    fun create(entity: Product) : Product
    fun update(entity: Product) : Product
    fun delete(entity: Product)
    fun delete(barCode : String)
}
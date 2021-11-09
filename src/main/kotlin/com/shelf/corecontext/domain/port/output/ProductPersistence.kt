package com.shelf.corecontext.domain.port.output

import com.shelf.corecontext.domain.entity.Product
import java.util.*

interface ProductPersistence {
    fun create(entity: Product) : Product
    fun delete(entity: Product) : Unit
    fun deleteByBarCode(id : String) : Unit
    fun findAll(page : Int, limit : Int, direction : String?) : List<Product>
    fun findByBarCode(id : String) : Optional<Product>
    fun findByName(name : String) : Optional<Product>
    fun update(entity : Product) : Product
}
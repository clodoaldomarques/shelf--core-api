package com.shelf.corecontext.domain.port.input

import com.shelf.corecontext.domain.entity.Store
import java.util.*

interface StoreUseCase {
    fun findAll(page : Int, limit : Int, direction : String?): List<Store>
    fun findById(id : Int): Optional<Store>
    fun create(entity: Store) : Store
    fun update(entity: Store) : Store
    fun delete(entity: Store)
    fun delete(id: Int)
}
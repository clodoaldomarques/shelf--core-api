package com.shelf.corecontext.domain.port.`in`

import com.shelf.corecontext.domain.entity.Store
import java.util.*

interface StoreUseCase {
    fun findAll(page : Int?, limit : Int?, direction : String?): List<Store>
    fun findById(id : Int): Optional<Store>
    fun create(store: Store) : Store
    fun update(store: Store) : Store
    fun delete(store: Store)
    fun delete(id: Int)
}
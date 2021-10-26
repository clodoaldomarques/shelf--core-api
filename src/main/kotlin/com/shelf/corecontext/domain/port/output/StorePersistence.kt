package com.shelf.corecontext.domain.port.output

import com.shelf.corecontext.domain.entity.Store
import java.util.*

interface StorePersistence {
    fun create(store: Store) : Store
    fun delete(store: Store) : Unit
    fun deleteById(id : Int) : Unit
    fun findAll(page : Int?, limit : Int?, direction : String?) : List<Store>
    fun findById(id : Int) : Optional<Store>
    fun findByName(name : String) : Optional<Store>
    fun update(store : Store) : Store

}
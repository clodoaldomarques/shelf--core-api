package com.shelf.corecontext.domain.port.out

import com.shelf.corecontext.domain.entity.Category
import com.shelf.corecontext.domain.entity.Store

interface DeleteStorePort {
    fun delete(store: Store) : Unit
    fun deleteById(id : Int) : Unit
}
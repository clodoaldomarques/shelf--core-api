package com.shelf.corecontext.domain.port.out

import com.shelf.corecontext.domain.entity.Category
import com.shelf.corecontext.domain.entity.Store

interface CreateStorePort {
    fun create( store: Store) : Store
}
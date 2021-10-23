package com.shelf.corecontext.domain.port.out

import com.shelf.corecontext.domain.entity.Category

interface UpdateStorePort {
    fun updateCategory(category : Category) : Category
}
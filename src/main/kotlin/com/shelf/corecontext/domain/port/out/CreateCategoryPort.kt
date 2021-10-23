package com.shelf.corecontext.domain.port.out

import com.shelf.corecontext.domain.entity.Category

interface CreateCategoryPort {
    fun create(category : Category) : Category
}
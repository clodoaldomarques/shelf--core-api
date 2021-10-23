package com.shelf.corecontext.domain.port.out

import com.shelf.corecontext.domain.entity.Category

interface UpdateCategoryPort {
    fun update(category : Category) : Category
}
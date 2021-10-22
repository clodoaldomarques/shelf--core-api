package com.shelf.corecontext.domain.port.out

import com.shelf.corecontext.domain.entity.Category

interface UpdateCategoryPort {
    fun updateCategory(category : Category) : Category
}
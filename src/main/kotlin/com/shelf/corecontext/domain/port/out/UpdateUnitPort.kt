package com.shelf.corecontext.domain.port.out

import com.shelf.corecontext.domain.entity.Category

interface UpdateUnitPort {
    fun updateCategory(category : Category) : Category
}
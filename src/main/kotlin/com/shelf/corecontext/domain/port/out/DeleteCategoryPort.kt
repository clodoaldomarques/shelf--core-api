package com.shelf.corecontext.domain.port.out

import com.shelf.corecontext.domain.entity.Category

interface DeleteCategoryPort {
    fun delete(category: Category) : Unit
    fun deleteById(id : Int) : Unit
}
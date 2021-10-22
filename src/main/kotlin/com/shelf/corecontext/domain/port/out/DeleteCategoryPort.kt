package com.shelf.corecontext.domain.port.out

import com.shelf.corecontext.domain.entity.Category

interface DeleteCategoryPort {
    fun deleteCategory(category: Category) : Unit
    fun deleteCategoryById(id : Int) : Unit
}
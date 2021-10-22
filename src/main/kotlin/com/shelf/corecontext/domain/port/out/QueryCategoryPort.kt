package com.shelf.corecontext.domain.port.out

import com.shelf.corecontext.domain.entity.Category

interface QueryCategoryPort {
    fun findAllCategory() : List<Category>
    fun findByCategoryId(id : Int) : List<Category>
}
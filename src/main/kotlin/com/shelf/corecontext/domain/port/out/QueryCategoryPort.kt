package com.shelf.corecontext.domain.port.out

import com.shelf.corecontext.domain.entity.Category
import java.util.*

interface QueryCategoryPort {
    fun findAllCategory(page : Int?, limit : Int?, direction : String?) : List<Category>
    fun findByCategoryId(id : Int) : Optional<Category>
    fun findCategoryByName(name : String) : Optional<Category>
}
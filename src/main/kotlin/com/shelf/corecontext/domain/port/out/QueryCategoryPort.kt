package com.shelf.corecontext.domain.port.out

import com.shelf.corecontext.domain.entity.Category
import java.util.*

interface QueryCategoryPort {
    fun findAll(page : Int?, limit : Int?, direction : String?) : List<Category>
    fun findById(id : Int) : Optional<Category>
    fun findByNameExactly(name : String) : Optional<Category>
}
package com.shelf.corecontext.domain.port.out

import com.shelf.corecontext.domain.entity.Category
import com.shelf.corecontext.domain.entity.Store
import java.util.*

interface QueryStorePort {
    fun findAll(page : Int?, limit : Int?, direction : String?) : List<Store>
    fun findByCategoryId(id : Int) : Optional<Store>
    fun findCategoryByName(name : String) : Optional<Category>
}
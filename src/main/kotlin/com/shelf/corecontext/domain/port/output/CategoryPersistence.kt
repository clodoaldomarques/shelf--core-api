package com.shelf.corecontext.domain.port.output

import com.shelf.corecontext.domain.entity.Category
import java.util.*

interface CategoryPersistence {
    fun create(category : Category) : Category
    fun delete(category: Category) : Unit
    fun deleteById(id : Int) : Unit
    fun findAll(page : Int, limit : Int, direction : String?) : List<Category>
    fun findById(id : Int) : Optional<Category>
    fun findByNameExactly(name : String) : Optional<Category>
    fun update(category : Category) : Category
}
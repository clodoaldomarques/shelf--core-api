package com.shelf.corecontext.domain.port.input

import com.shelf.corecontext.domain.entity.Category
import java.util.*

interface CategoryUseCase {
    fun findAll(page : Int, limit : Int, direction : String?): List<Category>
    fun findById(id : Int): Optional<Category>
    fun create(entity: Category) : Category
    fun update(entity: Category) : Category
    fun delete(entity: Category)
    fun delete(id: Int)
}
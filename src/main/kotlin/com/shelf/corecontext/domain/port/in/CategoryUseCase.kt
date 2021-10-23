package com.shelf.corecontext.domain.port.`in`

import com.shelf.corecontext.domain.entity.Category
import java.util.*

interface CategoryUseCase {
    fun findAll(page : Int?, limit : Int?, direction : String?): List<Category>
    fun findById(id : Int): Optional<Category>
    fun create(category: Category) : Category
    fun update(category: Category) : Category
    fun delete(category: Category)
    fun delete(id: Int)
}
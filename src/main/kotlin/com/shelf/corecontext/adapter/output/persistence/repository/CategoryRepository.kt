package com.shelf.corecontext.adapter.output.persistence.repository

import com.shelf.corecontext.adapter.output.persistence.model.CategoryTable
import com.shelf.corecontext.domain.entity.Category
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CategoryRepository : JpaRepository<CategoryTable, Int>{
    fun findByName(name: String): Optional<CategoryTable>
}
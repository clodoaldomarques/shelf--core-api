package com.shelf.corecontext.adapter.output.converter

import com.shelf.corecontext.adapter.output.persistence.model.CategoryTable
import com.shelf.corecontext.domain.entity.Category

fun Category.toTable(): CategoryTable {
    return CategoryTable(this.id, this.name)
}

fun CategoryTable.toEntity() : Category{
    return Category(this.id, this.name)
}
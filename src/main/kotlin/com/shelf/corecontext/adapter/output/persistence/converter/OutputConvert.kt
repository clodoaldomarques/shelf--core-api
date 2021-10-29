package com.shelf.corecontext.adapter.output.persistence.converter

import com.shelf.corecontext.adapter.output.persistence.model.CategoryTable
import com.shelf.corecontext.adapter.output.persistence.model.MeasureTable
import com.shelf.corecontext.adapter.output.persistence.model.StoreTable
import com.shelf.corecontext.domain.entity.Category
import com.shelf.corecontext.domain.entity.Measure
import com.shelf.corecontext.domain.entity.Store

fun Category.toTable(): CategoryTable {
    return CategoryTable(this.id, this.name)
}

fun CategoryTable.toEntity() : Category{
    return Category(this.id, this.name)
}

fun Store.toTable() : StoreTable{
    return StoreTable(this.id, this.name, this.registrationDate)
}

fun StoreTable.toEntity() : Store{
    return Store(this.id, this.name, this.registrationDate)
}

fun Measure.toTable() : MeasureTable{
    return MeasureTable(this.id, this.name, this.initials)
}

fun MeasureTable.toEntity(): Measure {
    return Measure(this.id, this.name, this.initials)
}

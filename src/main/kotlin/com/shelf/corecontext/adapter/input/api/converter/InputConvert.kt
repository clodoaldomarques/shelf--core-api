package com.shelf.corecontext.adapter.input.api.converter

import com.shelf.corecontext.adapter.input.api.controller.model.request.*
import com.shelf.corecontext.adapter.input.api.controller.model.response.CategoryResponseModel
import com.shelf.corecontext.adapter.input.api.controller.model.response.MeasureResponseModel
import com.shelf.corecontext.adapter.input.api.controller.model.response.StoreResponseModel
import com.shelf.corecontext.domain.entity.Category
import com.shelf.corecontext.domain.entity.Measure
import com.shelf.corecontext.domain.entity.Store
import java.time.OffsetDateTime

fun Category.toModel() : CategoryResponseModel{
    return CategoryResponseModel(this.id!!, this.name)
}

fun PostCategoryRequestModel.toEntity(): Category {
    return Category(id = null, name = this.name!!)
}

fun PutCategoryRequestModel.toEntity(id : Int): Category {
    return Category(id = id, name = this.name!!)
}

fun Measure.toModel() : MeasureResponseModel{
    return MeasureResponseModel(this.id!!, this.name, this.initials)
}

fun PostMeasureRequestModel.toEntity(): Measure{
    return Measure(null, this.name!!, this.initials!!)
}

fun PutMeasureRequestModel.toEntity(id : Int): Measure{
    return Measure(id, this.name!!, this.initials!!)
}

fun Store.toModel() : StoreResponseModel{
    return StoreResponseModel(this.id!!, this.name)
}

fun PostStoreRequestModel.toEntity(): Store {
    return Store(id = null, name = this.name!!, OffsetDateTime.now())
}

fun PutStoreRequestModel.toEntity(id : Int): Store {
    return Store(id = id, name = this.name!!, OffsetDateTime.now())
}
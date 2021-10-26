package com.shelf.corecontext.adapter.input.converter

import com.shelf.corecontext.adapter.input.controller.model.request.PostCategoryRequestModel
import com.shelf.corecontext.adapter.input.controller.model.request.PutCategoryRequestModel
import com.shelf.corecontext.adapter.input.controller.model.response.CategoryResponseModel
import com.shelf.corecontext.domain.entity.Category

fun Category.toModel() : CategoryResponseModel{
    return CategoryResponseModel(this.id!!, this.name)
}

fun PostCategoryRequestModel.toEntity(): Category {
    return Category(id = null, name = this.name!!)
}

fun PutCategoryRequestModel.toEntity(id : Int): Category {
    return Category(id = id, name = this.name!!)
}

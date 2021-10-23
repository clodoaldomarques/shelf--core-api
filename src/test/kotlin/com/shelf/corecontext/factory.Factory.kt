package com.shelf.corecontext

import com.shelf.corecontext.domain.entity.Category

fun buildCategory(
    id : Int? = null,
    name: String = "Fake Category"
) = Category(id = id, name = name)
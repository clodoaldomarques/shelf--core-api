package com.shelf.corecontext.domain.entity

import java.time.OffsetDateTime

data class Product(
    val barCode : String,
    val category: Category,
    val name : String,
    val description : String,
    val unit : Measure,
    val denyNegativeBalance : Boolean,
    val registrationDate : OffsetDateTime
)

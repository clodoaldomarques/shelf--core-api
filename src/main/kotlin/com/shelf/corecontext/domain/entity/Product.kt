package com.shelf.corecontext.domain.entity

import java.time.LocalDateTime

data class Product(
    val barCode : String,
    val category: Category,
    val name : String,
    val description : String,
    val unit : Unit,
    val denyNegativeBalance : Boolean,
    val registrationDate : LocalDateTime
)

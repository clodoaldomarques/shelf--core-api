package com.shelf.corecontext.domain.entity

import java.time.OffsetDateTime

data class Store(
    val id : Int?,
    val name : String,
    val registrationDate : OffsetDateTime
)

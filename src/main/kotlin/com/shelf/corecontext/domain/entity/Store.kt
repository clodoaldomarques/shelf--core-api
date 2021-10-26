package com.shelf.corecontext.domain.entity

import java.time.LocalDateTime

data class Store(
    val id : Int?,
    val name : String,
    val registrationDate : LocalDateTime
)

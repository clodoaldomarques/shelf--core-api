package com.shelf.corecontext.domain.entity

import java.math.BigDecimal
import java.time.LocalDateTime

data class Inventory(
    val id : Int?,
    val store : Store,
    val product : Product,
    val purchasePrice : BigDecimal,
    val salePrice : BigDecimal,
    val balance : BigDecimal,
    val registrationDate : LocalDateTime
)
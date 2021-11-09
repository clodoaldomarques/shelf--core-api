package com.shelf.corecontext.domain.entity

import com.shelf.corecontext.domain.exceptions.GenericBusinessException
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.OffsetDateTime

data class Inventory(
    val id : Int?,
    val store : Store,
    val product : Product,
    var purchasePrice : BigDecimal?,
    var salePrice : BigDecimal?,
    var balance : BigDecimal?,
    val registrationDate : OffsetDateTime
){
    internal fun increase(value : BigDecimal){
        balance = balance?: BigDecimal.ZERO
        balance = balance!!.add(value)
    }

    internal fun decrease(value: BigDecimal){
        balance = balance?: BigDecimal.ZERO
        balance = balance!!.subtract(value)
        if (product.denyNegativeBalance && balance!! < BigDecimal.ZERO){
            throw GenericBusinessException("Balance in stock cannot be less than zero")
        }
    }

    internal fun updatePurchasePrice(value: BigDecimal){
        purchasePrice = purchasePrice?: BigDecimal.ZERO
        if (value < BigDecimal.ZERO) throw GenericBusinessException("Negative value for purchase price")
        purchasePrice = value
    }

    internal fun updateSalePrice(value: BigDecimal){
        salePrice = salePrice?: BigDecimal.ZERO
        if (value < BigDecimal.ZERO) throw GenericBusinessException("Negative value for sale price")
        salePrice = value
    }

}
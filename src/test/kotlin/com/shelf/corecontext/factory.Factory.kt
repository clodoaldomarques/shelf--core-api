package com.shelf.corecontext

import com.shelf.corecontext.domain.entity.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.util.*

fun buildCategory(
    id : Int? = null,
    name: String = "Fake Category"
) = Category(id = id, name = name)

fun buildMeasure(
    id : Int? = null,
    name: String = "Fake MetricUnit",
    initials : String = "fmu"
) = Measure(id = id, name = name, initials = initials)

fun buildStore(
    id : Int? = null,
    name: String = "Fake Category"
) = Store(id = id, name = name, registrationDate = OffsetDateTime.now())


fun buildProduct(
    barCode : String = UUID.randomUUID().toString(),
    category: Category = buildCategory(),
    name : String = "Fake Product",
    description : String = "Fake Product Description",
    unit : Measure = buildMeasure(),
    denyNegativeBalance : Boolean = true
) = Product(barCode = barCode, category = category, name = name , description = description , unit = unit,
    denyNegativeBalance = denyNegativeBalance, registrationDate = OffsetDateTime.now())

fun buildInventory(
    id : Int? = null,
    store: Store = buildStore(),
    product: Product = buildProduct(),
    purchasePrice: BigDecimal = BigDecimal.ONE,
    salePrice : BigDecimal = BigDecimal.ONE,
    balance : BigDecimal = BigDecimal.TEN
) = Inventory(id = id,
    store = store ,
    product = product,
    purchasePrice = purchasePrice, salePrice = salePrice, balance = balance, registrationDate = OffsetDateTime.now())
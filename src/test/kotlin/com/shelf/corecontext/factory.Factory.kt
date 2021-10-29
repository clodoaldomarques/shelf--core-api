package com.shelf.corecontext

import com.shelf.corecontext.domain.entity.Category
import com.shelf.corecontext.domain.entity.Measure
import com.shelf.corecontext.domain.entity.Store
import java.time.LocalDateTime
import java.time.OffsetDateTime

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
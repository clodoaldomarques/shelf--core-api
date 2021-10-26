package com.shelf.corecontext.domain.port.input

import com.shelf.corecontext.domain.entity.MetricUnit
import java.util.*

interface UnitUseCase {
    fun findAll(page : Int?, limit : Int?, direction : String?): List<MetricUnit>
    fun findById(id : Int): Optional<MetricUnit>
    fun create(unit: MetricUnit) : MetricUnit
    fun update(unit: MetricUnit) : MetricUnit
    fun delete(unit: MetricUnit)
    fun delete(id: Int)
}
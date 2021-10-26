package com.shelf.corecontext.domain.port.output

import com.shelf.corecontext.domain.entity.Category
import com.shelf.corecontext.domain.entity.MetricUnit
import java.util.*

interface MetricUnitPersistence {
    fun create( metricUnit: MetricUnit) : MetricUnit
    fun delete(unit: MetricUnit) : Unit
    fun deleteById(id : Int) : Unit
    fun findAll(page : Int?, limit : Int?, direction : String?) : List<MetricUnit>
    fun findById(id : Int) : Optional<MetricUnit>
    fun findByName(name : String) : Optional<MetricUnit>
    fun update(metricUnit: MetricUnit) : MetricUnit

}
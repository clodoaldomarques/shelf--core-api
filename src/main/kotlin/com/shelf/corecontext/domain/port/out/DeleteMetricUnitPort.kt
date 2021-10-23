package com.shelf.corecontext.domain.port.out

import com.shelf.corecontext.domain.entity.MetricUnit

interface DeleteMetricUnitPort {
    fun delete(unit: MetricUnit) : kotlin.Unit
    fun deleteById(id : Int) : kotlin.Unit
}
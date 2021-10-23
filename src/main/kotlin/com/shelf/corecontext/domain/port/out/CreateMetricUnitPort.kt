package com.shelf.corecontext.domain.port.out

import com.shelf.corecontext.domain.entity.MetricUnit

interface CreateMetricUnitPort {
    fun create( metricUnit: MetricUnit) : MetricUnit
}
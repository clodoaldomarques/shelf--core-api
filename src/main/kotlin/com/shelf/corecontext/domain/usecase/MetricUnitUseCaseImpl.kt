package com.shelf.corecontext.domain.usecase

import com.shelf.corecontext.domain.entity.MetricUnit
import com.shelf.corecontext.domain.exceptions.ResourceExistsException
import com.shelf.corecontext.domain.exceptions.ResourceNotFoundException
import com.shelf.corecontext.domain.port.input.MetricUnitUseCase
import com.shelf.corecontext.domain.port.output.MetricUnitPersistence
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class MetricUnitUseCaseImpl(
    private val metricUnitPersistence: MetricUnitPersistence,
) : MetricUnitUseCase {
    override fun findAll(page: Int, limit: Int, direction: String?): List<MetricUnit> {
        return metricUnitPersistence.findAll(page,  limit, direction)
    }

    override fun findById(id : Int): Optional<MetricUnit> {
        return metricUnitPersistence.findById(id)
    }

    override fun create(metricUnit: MetricUnit) : MetricUnit {
        val metricUnitSaved = metricUnitPersistence.findByName(metricUnit.name)
        if (metricUnitSaved.isPresent) throw ResourceExistsException("MetricUnit already registered")
        return metricUnitPersistence.create(metricUnit)
    }

    override fun update(metricUnit: MetricUnit) : MetricUnit{
        val categorySaved = metricUnitPersistence.findById(metricUnit.id!!)
        if (categorySaved.isEmpty) throw ResourceNotFoundException("MetricUnit not found")
        return metricUnitPersistence.update(metricUnit)
    }

    override fun delete(metricUnit: MetricUnit){
        val categorySaved = metricUnitPersistence.findById(metricUnit.id!!)
        if (categorySaved.isEmpty) throw ResourceNotFoundException("MetricUnit not found")
        metricUnitPersistence.delete(metricUnit)
    }

    override fun delete(id: Int){
        val categorySaved = metricUnitPersistence.findById(id)
        if (categorySaved.isEmpty) throw ResourceNotFoundException("MetricUnit not found")
        metricUnitPersistence.deleteById(id)
    }

}
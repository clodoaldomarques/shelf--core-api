package com.shelf.corecontext.domain.usecase

import com.shelf.corecontext.domain.entity.Measure
import com.shelf.corecontext.domain.exceptions.ResourceExistsException
import com.shelf.corecontext.domain.exceptions.ResourceNotFoundException
import com.shelf.corecontext.domain.port.input.MeasureUseCase
import com.shelf.corecontext.domain.port.output.MeasurePersistence
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class MeasureUseCaseImpl(
    private val measurePersistence: MeasurePersistence,
) : MeasureUseCase {
    override fun findAll(page: Int, limit: Int, direction: String?): List<Measure> {
        return measurePersistence.findAll(page,  limit, direction)
    }

    override fun findById(id : Int): Optional<Measure> {
        return measurePersistence.findById(id)
    }

    override fun create(unit: Measure) : Measure {
        val measureSaved = measurePersistence.findByName(unit.name)
        if (measureSaved.isPresent) throw ResourceExistsException("Measure already registered")
        return measurePersistence.create(unit)
    }

    override fun update(unit: Measure) : Measure{
        val categorySaved = measurePersistence.findById(unit.id!!)
        if (categorySaved.isEmpty) throw ResourceNotFoundException("Measure not found")
        return measurePersistence.update(unit)
    }

    override fun delete(unit: Measure){
        val measureSaved = measurePersistence.findById(unit.id!!)
        if (measureSaved.isEmpty) throw ResourceNotFoundException("Measure not found")
        measurePersistence.delete(unit)
    }

    override fun delete(id: Int){
        val categorySaved = measurePersistence.findById(id)
        if (categorySaved.isEmpty) throw ResourceNotFoundException("Measure not found")
        measurePersistence.deleteById(id)
    }

}
package com.shelf.corecontext.adapter.output.persistence

import com.shelf.corecontext.adapter.output.persistence.converter.toEntity
import com.shelf.corecontext.adapter.output.persistence.converter.toTable
import com.shelf.corecontext.adapter.output.persistence.repository.MeasureRepository
import com.shelf.corecontext.domain.entity.Measure
import com.shelf.corecontext.domain.port.output.MeasurePersistence
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import java.util.*

@Component
class MeasurePersistenceImpl (private val repository : MeasureRepository ) : MeasurePersistence {

    override fun create(unit: Measure): Measure {
        return repository.save(unit.toTable()).toEntity()
    }

    override fun delete(unit: Measure) {
        repository.delete(unit.toTable())
    }

    override fun deleteById(id: Int) {
        repository.deleteById(id)
    }

    override fun findAll(page: Int, limit: Int, direction: String?): List<Measure> {
        val pageNumber = if(page >= 0) page else 0
        val pageSize = if (limit > 0) limit else 10
        val sortDirection = if ("desc".equals(direction, ignoreCase = true)) Sort.Direction.DESC else Sort.Direction.ASC
        val sort = Sort.by(sortDirection, "name")
        val pageable = PageRequest.of(pageNumber, pageSize, sort)
        return repository.findAll(pageable).map { it.toEntity() }.toList()
    }

    override fun findById(id: Int): Optional<Measure> {
        val optional = repository.findById(id)
        return if(optional.isPresent){
            Optional.of(optional.get().toEntity())
        } else {
            Optional.empty()
        }
    }

    override fun findByName(name: String): Optional<Measure> {
        val optional = repository.findByName(name)
        return if(optional.isPresent){
            Optional.of(optional.get().toEntity())
        } else {
            Optional.empty()
        }
    }

    override fun update(unit: Measure): Measure {
        return repository.save(unit.toTable()).toEntity()
    }
}
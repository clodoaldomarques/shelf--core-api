package com.shelf.corecontext.domain.port.input

import com.shelf.corecontext.domain.entity.Measure
import java.util.*

interface MeasureUseCase {
    fun findAll(page : Int, limit : Int, direction : String?): List<Measure>
    fun findById(id : Int): Optional<Measure>
    fun create(entity: Measure) : Measure
    fun update(entity: Measure) : Measure
    fun delete(entity: Measure)
    fun delete(id: Int)
}
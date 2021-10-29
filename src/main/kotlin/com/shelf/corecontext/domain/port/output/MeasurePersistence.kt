package com.shelf.corecontext.domain.port.output

import com.shelf.corecontext.domain.entity.Measure
import java.util.*

interface MeasurePersistence {
    fun create( unit : Measure) : Measure
    fun delete(unit: Measure) : Unit
    fun deleteById(id : Int) : Unit
    fun findAll(page : Int, limit : Int, direction : String?) : List<Measure>
    fun findById(id : Int) : Optional<Measure>
    fun findByName(name : String) : Optional<Measure>
    fun update(unit: Measure) : Measure

}
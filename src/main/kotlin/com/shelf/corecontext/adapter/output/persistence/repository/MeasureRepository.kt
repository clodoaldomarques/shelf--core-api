package com.shelf.corecontext.adapter.output.persistence.repository

import com.shelf.corecontext.adapter.output.persistence.model.MeasureTable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MeasureRepository : JpaRepository<MeasureTable, Int>{
    fun findByName(name: String): Optional<MeasureTable>
}
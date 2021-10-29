package com.shelf.corecontext.adapter.output.persistence.repository

import com.shelf.corecontext.adapter.output.persistence.model.StoreTable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface StoreRepository : JpaRepository<StoreTable, Int>{
    fun findByName(name: String): Optional<StoreTable>
}
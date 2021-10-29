package com.shelf.corecontext.adapter.output.persistence

import com.shelf.corecontext.adapter.output.persistence.converter.toEntity
import com.shelf.corecontext.adapter.output.persistence.converter.toTable
import com.shelf.corecontext.adapter.output.persistence.repository.StoreRepository
import com.shelf.corecontext.domain.entity.Store
import com.shelf.corecontext.domain.port.output.StorePersistence
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import java.util.*

@Component
class StorePersistenceImpl (private val repository : StoreRepository ) : StorePersistence {

    override fun create(store: Store): Store {
        return repository.save(store.toTable()).toEntity()
    }

    override fun delete(store: Store) {
        repository.delete(store.toTable())
    }

    override fun deleteById(id: Int) {
        repository.deleteById(id)
    }

    override fun findAll(page: Int, limit: Int, direction: String?): List<Store> {
        val pageNumber = if(page >= 0) page else 0
        val pageSize = if (limit > 0) limit else 10
        val sortDirection = if ("desc".equals(direction, ignoreCase = true)) Sort.Direction.DESC else Sort.Direction.ASC
        val sort = Sort.by(sortDirection, "name")
        val pageable = PageRequest.of(pageNumber, pageSize, sort)
        return repository.findAll(pageable).map { it.toEntity() }.toList()
    }

    override fun findById(id: Int): Optional<Store> {
        val optional = repository.findById(id)
        return if(optional.isPresent){
            Optional.of(optional.get().toEntity())
        } else {
            Optional.empty()
        }
    }

    override fun findByName(name: String): Optional<Store> {
        val optional = repository.findByName(name)
        return if(optional.isPresent){
            Optional.of(optional.get().toEntity())
        } else {
            Optional.empty()
        }
    }

    override fun update(store: Store): Store {
        return repository.save(store.toTable()).toEntity()
    }
}
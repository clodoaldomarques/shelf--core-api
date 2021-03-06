package com.shelf.corecontext.adapter.output.persistence

import com.shelf.corecontext.adapter.output.persistence.converter.toEntity
import com.shelf.corecontext.adapter.output.persistence.converter.toTable
import com.shelf.corecontext.adapter.output.persistence.repository.CategoryRepository
import com.shelf.corecontext.domain.entity.Category
import com.shelf.corecontext.domain.port.output.CategoryPersistence
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import java.util.*

@Component
class CategoryPersistenceImpl (private val repository : CategoryRepository ) : CategoryPersistence {

    override fun create(category: Category): Category {
        return repository.save(category.toTable()).toEntity()
    }

    override fun delete(category: Category) {
        repository.delete(category.toTable())
    }

    override fun deleteById(id: Int) {
        repository.deleteById(id)
    }

    override fun findAll(page: Int, limit: Int, direction: String?): List<Category> {
        val pageNumber = if(page >= 0) page else 0
        val pageSize = if (limit > 0) limit else 10
        val sortDirection = if ("desc".equals(direction, ignoreCase = true)) Sort.Direction.DESC else Sort.Direction.ASC
        val sort = Sort.by(sortDirection, "name")
        val pageable = PageRequest.of(pageNumber, pageSize, sort)
        return repository.findAll(pageable).map { it.toEntity() }.toList()
    }

    override fun findById(id: Int): Optional<Category> {
        val optional = repository.findById(id)
        return if(optional.isPresent){
            Optional.of(optional.get().toEntity())
        } else {
            Optional.empty()
        }
    }

    override fun findByNameExactly(name: String): Optional<Category> {
        val optional = repository.findByName(name)
        return if(optional.isPresent){
            Optional.of(optional.get().toEntity())
        } else {
            Optional.empty()
        }
    }

    override fun update(category: Category): Category {
        return repository.save(category.toTable()).toEntity()
    }
}
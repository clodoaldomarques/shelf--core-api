package com.shelf.corecontext.adapter.out.persistence.repository

import com.shelf.corecontext.domain.entity.Category
import com.shelf.corecontext.domain.port.out.CreateCategoryPort
import com.shelf.corecontext.domain.port.out.DeleteCategoryPort
import com.shelf.corecontext.domain.port.out.QueryCategoryPort
import com.shelf.corecontext.domain.port.out.UpdateCategoryPort
import java.util.*

class CategoryPersistence : CreateCategoryPort, DeleteCategoryPort, QueryCategoryPort, UpdateCategoryPort {
    override fun create(category: Category): Category {
        TODO("Not yet implemented")
    }

    override fun delete(category: Category) {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int) {
        TODO("Not yet implemented")
    }

    override fun findAll(page: Int?, limit: Int?, direction: String?): List<Category> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): Optional<Category> {
        TODO("Not yet implemented")
    }

    override fun findByNameExactly(name: String): Optional<Category> {
        TODO("Not yet implemented")
    }

    override fun update(category: Category): Category {
        TODO("Not yet implemented")
    }
}
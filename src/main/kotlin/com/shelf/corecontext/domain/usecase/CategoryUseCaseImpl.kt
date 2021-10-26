package com.shelf.corecontext.domain.usecase

import com.shelf.corecontext.domain.entity.Category
import com.shelf.corecontext.domain.exceptions.ResourceExistsException
import com.shelf.corecontext.domain.exceptions.ResourceNotFoundException
import com.shelf.corecontext.domain.port.input.CategoryUseCase
import com.shelf.corecontext.domain.port.output.CategoryPersistence
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class CategoryUseCaseImpl(
    private val categoryPersistence: CategoryPersistence,
) : CategoryUseCase {
    override fun findAll(page : Int, limit : Int, direction : String?): List<Category> {
        return categoryPersistence.findAll(page,  limit, direction)
    }

    override fun findById(id : Int): Optional<Category> {
        return categoryPersistence.findById(id)
    }

    override fun create(category: Category) : Category {
        val categorySaved = categoryPersistence.findByNameExactly(category.name)
        if (categorySaved.isPresent) throw ResourceExistsException("Category already registered")
        return categoryPersistence.create(category)
    }

    override fun update(category: Category) : Category{
        val categorySaved = categoryPersistence.findById(category.id!!)
        if (categorySaved.isEmpty) throw ResourceNotFoundException("Category not found")
        return categoryPersistence.update(category)
    }

    override fun delete(category: Category){
        val categorySaved = categoryPersistence.findById(category.id!!)
        if (categorySaved.isEmpty) throw ResourceNotFoundException("Category not found")
        categoryPersistence.delete(category)
    }

    override fun delete(id: Int){
        val categorySaved = categoryPersistence.findById(id)
        if (categorySaved.isEmpty) throw ResourceNotFoundException("Category not found")
        categoryPersistence.deleteById(id)
    }

}
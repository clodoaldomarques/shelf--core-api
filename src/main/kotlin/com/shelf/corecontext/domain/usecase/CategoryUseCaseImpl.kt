package com.shelf.corecontext.domain.usecase

import com.shelf.corecontext.domain.entity.Category
import com.shelf.corecontext.domain.exceptions.ResourceExistsException
import com.shelf.corecontext.domain.exceptions.ResourceNotFoundException
import com.shelf.corecontext.domain.port.`in`.CategoryUseCase
import com.shelf.corecontext.domain.port.out.CreateCategoryPort
import com.shelf.corecontext.domain.port.out.DeleteCategoryPort
import com.shelf.corecontext.domain.port.out.QueryCategoryPort
import com.shelf.corecontext.domain.port.out.UpdateCategoryPort
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class CategoryUseCaseImpl(
    val createCategoryPort: CreateCategoryPort,
    val deleteCategoryPort: DeleteCategoryPort,
    val queryCategoryPort: QueryCategoryPort,
    val updateCategoryPort: UpdateCategoryPort
) : CategoryUseCase {
    override fun findAll(page : Int?, limit : Int?, direction : String?): List<Category> {
        return queryCategoryPort.findAll(page,  limit, direction)
    }

    override fun findById(id : Int): Optional<Category> {
        return queryCategoryPort.findById(id)
    }

    override fun create(category: Category) : Category {
        val categorySaved = queryCategoryPort.findByNameExactly(category.name)
        if (categorySaved.isPresent) throw ResourceExistsException("Category already registered")
        return createCategoryPort.create(category)
    }

    override fun update(category: Category) : Category{
        val categorySaved = queryCategoryPort.findById(category.id!!)
        if (categorySaved.isEmpty) throw ResourceNotFoundException("Category not found")
        return updateCategoryPort.update(category)
    }

    override fun delete(category: Category){
        val categorySaved = queryCategoryPort.findById(category.id!!)
        if (categorySaved.isEmpty) throw ResourceNotFoundException("Category not found")
        deleteCategoryPort.delete(category)
    }

    override fun delete(id: Int){
        val categorySaved = queryCategoryPort.findById(id)
        if (categorySaved.isEmpty) throw ResourceNotFoundException("Category not found")
        deleteCategoryPort.deleteById(id)
    }

}
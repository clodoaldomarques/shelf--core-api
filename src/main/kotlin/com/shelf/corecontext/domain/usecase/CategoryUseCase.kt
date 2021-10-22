package com.shelf.corecontext.domain.usecase

import com.shelf.corecontext.domain.entity.Category
import com.shelf.corecontext.domain.exceptions.ResourceExistsException
import com.shelf.corecontext.domain.exceptions.ResourceNotFoundException
import com.shelf.corecontext.domain.port.out.CreateCategoryPort
import com.shelf.corecontext.domain.port.out.DeleteCategoryPort
import com.shelf.corecontext.domain.port.out.QueryCategoryPort
import com.shelf.corecontext.domain.port.out.UpdateCategoryPort
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional


@Service
@Transactional
class CategoryUseCase(
    val createCategoryPort: CreateCategoryPort,
    val deleteCategoryPort: DeleteCategoryPort,
    val queryCategoryPort: QueryCategoryPort,
    val updateCategoryPort: UpdateCategoryPort
) {
    fun findAll(page : Int?, limit : Int?, direction : String?): List<Category> {
        return queryCategoryPort.findAllCategory(page,  limit, direction)
    }

    fun findById(id : Int): Optional<Category> {
        return queryCategoryPort.findByCategoryId(id)
    }

    fun create(category: Category) : Category {
        val categorySaved = queryCategoryPort.findCategoryByName(category.name)
        if (categorySaved.isPresent) throw ResourceExistsException("Category already registered")
        return createCategoryPort.createCategory(category)
    }

    fun update(category: Category) : Category{
        val categorySaved = queryCategoryPort.findByCategoryId(category.id)
        if (categorySaved.isEmpty) throw ResourceNotFoundException("Category not found")
        return updateCategoryPort.updateCategory(category)
    }

    fun delete(category: Category){
        val categorySaved = queryCategoryPort.findByCategoryId(category.id)
        if (categorySaved.isEmpty) throw ResourceNotFoundException("Category not found")
        deleteCategoryPort.deleteCategory(category)
    }

    fun delete(id: Int){
        val categorySaved = queryCategoryPort.findByCategoryId(id)
        if (categorySaved.isEmpty) throw ResourceNotFoundException("Category not found")
        deleteCategoryPort.deleteCategoryById(id)
    }

}
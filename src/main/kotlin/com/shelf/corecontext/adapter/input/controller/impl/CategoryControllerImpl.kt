package com.shelf.corecontext.adapter.input.controller.impl

import com.shelf.corecontext.adapter.input.controller.CategoryController
import com.shelf.corecontext.adapter.input.controller.model.request.PostCategoryRequestModel
import com.shelf.corecontext.adapter.input.controller.model.request.PutCategoryRequestModel
import com.shelf.corecontext.adapter.input.controller.model.response.CategoryResponseModel
import com.shelf.corecontext.adapter.input.converter.toEntity
import com.shelf.corecontext.adapter.input.converter.toModel
import com.shelf.corecontext.domain.port.input.CategoryUseCase
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("categories")
class CategoryControllerImpl (
    val categoryUseCase: CategoryUseCase
    ) : CategoryController {

    @GetMapping
    override fun findAll(@RequestParam(value = "page", defaultValue = "0") page: Int,
                         @RequestParam(value = "limit", defaultValue = "10") limit: Int,
                         @RequestParam(value = "direction", defaultValue = "asc") direction: String?): ResponseEntity<List<CategoryResponseModel>> {
        val categories = categoryUseCase.findAll(page, limit, direction).map { it.toModel() }.toList()
        return ResponseEntity.ok(categories)
    }

    @GetMapping("/{id}")
    override fun findById(@PathVariable id: Int): ResponseEntity<CategoryResponseModel> {
        val category = categoryUseCase.findById(id).map { it.toModel() }
        return if (category.isPresent){
            ResponseEntity.ok(category.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    override fun insert(@Validated @RequestBody model: PostCategoryRequestModel): ResponseEntity<CategoryResponseModel> {
        val category = categoryUseCase.create(model.toEntity()).toModel()
        return ResponseEntity.ok(category)
    }

    @PutMapping("/{id}")
    override fun update(@PathVariable id: Int, @Valid @RequestBody model: PutCategoryRequestModel): ResponseEntity<CategoryResponseModel> {
        val category = categoryUseCase.update(model.toEntity(id))
        return ResponseEntity.ok(category.toModel())
    }

    @DeleteMapping("/{id}")
    override fun delete(@PathVariable id: Int): ResponseEntity<Unit> {
        categoryUseCase.delete(id)
        return ResponseEntity.ok(Unit)
    }
}
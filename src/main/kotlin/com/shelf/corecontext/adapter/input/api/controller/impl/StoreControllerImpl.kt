package com.shelf.corecontext.adapter.input.api.controller.impl

import com.shelf.corecontext.adapter.input.api.controller.StoreController
import com.shelf.corecontext.adapter.input.api.controller.model.request.PostStoreRequestModel
import com.shelf.corecontext.adapter.input.api.controller.model.request.PutStoreRequestModel
import com.shelf.corecontext.adapter.input.api.controller.model.response.StoreResponseModel
import com.shelf.corecontext.adapter.input.api.converter.toEntity
import com.shelf.corecontext.adapter.input.api.converter.toModel
import com.shelf.corecontext.domain.port.input.StoreUseCase
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("stories")
class StoreControllerImpl (
    val storeUseCase: StoreUseCase
    ) : StoreController {

    @GetMapping
    override fun findAll(@RequestParam(value = "page", defaultValue = "0") page: Int,
                         @RequestParam(value = "limit", defaultValue = "10") limit: Int,
                         @RequestParam(value = "direction", defaultValue = "asc") direction: String?): ResponseEntity<List<StoreResponseModel>> {
        val list = storeUseCase.findAll(page, limit, direction).map { it.toModel() }.toList()
        return ResponseEntity.ok(list)
    }

    @GetMapping("/{id}")
    override fun findById(@PathVariable id: Int): ResponseEntity<StoreResponseModel> {
        val optional = storeUseCase.findById(id).map { it.toModel() }
        return if (optional.isPresent){
            ResponseEntity.ok(optional.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    override fun insert(@Validated @RequestBody model: PostStoreRequestModel): ResponseEntity<StoreResponseModel> {
        val StoreResponseModel = storeUseCase.create(model.toEntity()).toModel()
        return ResponseEntity.ok(StoreResponseModel)
    }

    @PutMapping("/{id}")
    override fun update(@PathVariable id: Int, @Valid @RequestBody model: PutStoreRequestModel): ResponseEntity<StoreResponseModel> {
        val Store = storeUseCase.update(model.toEntity(id))
        return ResponseEntity.ok(Store.toModel())
    }

    @DeleteMapping("/{id}")
    override fun delete(@PathVariable id: Int): ResponseEntity<Unit> {
        storeUseCase.delete(id)
        return ResponseEntity.ok(Unit)
    }
}
package com.shelf.corecontext.adapter.input.api.controller.impl

import com.shelf.corecontext.adapter.input.api.controller.MeasureController
import com.shelf.corecontext.adapter.input.api.controller.model.request.PostMeasureRequestModel
import com.shelf.corecontext.adapter.input.api.controller.model.request.PutMeasureRequestModel
import com.shelf.corecontext.adapter.input.api.controller.model.response.MeasureResponseModel
import com.shelf.corecontext.adapter.input.api.converter.toEntity
import com.shelf.corecontext.adapter.input.api.converter.toModel
import com.shelf.corecontext.domain.port.input.MeasureUseCase
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("measures")
class MeasureControllerImpl (
    val measureUseCase: MeasureUseCase
    ) : MeasureController {

    @GetMapping
    override fun findAll(@RequestParam(value = "page", defaultValue = "0") page: Int,
                         @RequestParam(value = "limit", defaultValue = "10") limit: Int,
                         @RequestParam(value = "direction", defaultValue = "asc") direction: String?): ResponseEntity<List<MeasureResponseModel>> {
        val list = measureUseCase.findAll(page, limit, direction).map { it.toModel() }.toList()
        return ResponseEntity.ok(list)
    }

    @GetMapping("/{id}")
    override fun findById(@PathVariable id: Int): ResponseEntity<MeasureResponseModel> {
        val optional = measureUseCase.findById(id).map { it.toModel() }
        return if (optional.isPresent){
            ResponseEntity.ok(optional.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    override fun insert(@Validated @RequestBody model: PostMeasureRequestModel): ResponseEntity<MeasureResponseModel> {
        val measureResponseModel = measureUseCase.create(model.toEntity()).toModel()
        return ResponseEntity.ok(measureResponseModel)
    }

    @PutMapping("/{id}")
    override fun update(@PathVariable id: Int, @Valid @RequestBody model: PutMeasureRequestModel): ResponseEntity<MeasureResponseModel> {
        val measure = measureUseCase.update(model.toEntity(id))
        return ResponseEntity.ok(measure.toModel())
    }

    @DeleteMapping("/{id}")
    override fun delete(@PathVariable id: Int): ResponseEntity<Unit> {
        measureUseCase.delete(id)
        return ResponseEntity.ok(Unit)
    }
}
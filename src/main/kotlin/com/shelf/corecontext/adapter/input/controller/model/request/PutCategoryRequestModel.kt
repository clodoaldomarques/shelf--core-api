package com.shelf.corecontext.adapter.input.controller.model.request

import javax.validation.constraints.NotBlank

data class PutCategoryRequestModel (
    @field:NotBlank(message = "Name required")
    val name: String? = null
)

package com.shelf.corecontext.adapter.input.api.controller.model.request

import javax.validation.constraints.NotBlank

data class PostStoreRequestModel(
    @field:NotBlank(message = "Name is required")
    val name: String? = null
)

package com.shelf.corecontext.adapter.input.controller.model.request

import javax.validation.constraints.NotBlank

data class PostCategoryRequestModel (
    @NotBlank(message = "Name required")
    val name: String? = null
)

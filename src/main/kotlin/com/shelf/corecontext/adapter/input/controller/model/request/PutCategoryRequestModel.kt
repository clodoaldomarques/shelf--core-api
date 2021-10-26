package com.shelf.corecontext.adapter.input.controller.model.request

import javax.validation.constraints.NotBlank

data class PutCategoryRequestModel (
    @NotBlank(message = "Name required")
    val name: String? = null
)
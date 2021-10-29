package com.shelf.corecontext.adapter.input.api.controller.model.request

import javax.validation.constraints.NotBlank

data class PutMeasureRequestModel(
    @field:NotBlank(message = "Name is required")
    val name: String? = null,

    @field:NotBlank(message = "Initials is required")
    val initials: String? = null

)

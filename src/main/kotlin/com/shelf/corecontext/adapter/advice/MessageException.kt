package com.shelf.corecontext.adapter.advice

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.OffsetDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class MessageException(
    var status: Int = 0,
    var titulo: String? = null,
    var dataHora: OffsetDateTime? = null,
    var errors: List<Error>? = null
)

data class Error (
    var name: String? = null,
    var message: String? = null
)

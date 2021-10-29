package com.shelf.corecontext.adapter.input.api.controller

import com.shelf.corecontext.adapter.input.api.advice.MessageException
import com.shelf.corecontext.adapter.input.api.controller.model.request.PostCategoryRequestModel
import com.shelf.corecontext.adapter.input.api.controller.model.request.PutCategoryRequestModel
import com.shelf.corecontext.adapter.input.api.controller.model.response.CategoryResponseModel
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

interface CategoryController{
    companion object {
        private const val apiDescription: String = "API Category version 1.0.0"
    }

    @Operation(summary = "Find all category", description = "", tags = [Companion.apiDescription])
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Success",
            content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE)]
        ), ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = [Content(schema = Schema(implementation = MessageException::class))]
        ), ApiResponse(
            responseCode = "401",
            description = "Unauthorized",
            content = [Content(schema = Schema(implementation = MessageException::class))]
        ), ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = [Content(schema = Schema(implementation = MessageException::class))]
        ), ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = [Content(schema = Schema(implementation = MessageException::class))]
        )]
    )
    fun findAll(page: Int, limit: Int, direction: String?): ResponseEntity<List<CategoryResponseModel>>

    @Operation(summary = "Find category by identification", description = "", tags = [Companion.apiDescription])
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Success",
            content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE)]
        ), ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = [Content(schema = Schema(implementation = MessageException::class))]
        ), ApiResponse(
            responseCode = "401",
            description = "Unauthorized",
            content = [Content(schema = Schema(implementation = MessageException::class))]
        ), ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = [Content(schema = Schema(implementation = MessageException::class))]
        ), ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = [Content(schema = Schema(implementation = MessageException::class))]
        )]
    )
    fun findById(@PathVariable id: Int): ResponseEntity<CategoryResponseModel>

    @Operation(summary = "Insert a category", description = "", tags = [Companion.apiDescription])
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Success",
            content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE)]
        ), ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = [Content(schema = Schema(implementation = MessageException::class))]
        ), ApiResponse(
            responseCode = "401",
            description = "Unauthorized",
            content = [Content(schema = Schema(implementation = MessageException::class))]
        ), ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = [Content(schema = Schema(implementation = MessageException::class))]
        ), ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = [Content(schema = Schema(implementation = MessageException::class))]
        )]
    )
    fun insert( model: PostCategoryRequestModel): ResponseEntity<CategoryResponseModel>

    @Operation(summary = "Update a category", description = "", tags = [Companion.apiDescription])
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Success",
            content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE)]
        ), ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = [Content(schema = Schema(implementation = MessageException::class))]
        ), ApiResponse(
            responseCode = "401",
            description = "Unauthorized",
            content = [Content(schema = Schema(implementation = MessageException::class))]
        ), ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = [Content(schema = Schema(implementation = MessageException::class))]
        ), ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = [Content(schema = Schema(implementation = MessageException::class))]
        )]
    )
    fun update(@PathVariable id: Int, @RequestBody model: PutCategoryRequestModel): ResponseEntity<CategoryResponseModel>

    @Operation(summary = "Remove a category", description = "", tags = [Companion.apiDescription])
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "204",
            description = "No Content"
        ), ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = [Content(schema = Schema(implementation = MessageException::class))]
        ), ApiResponse(
            responseCode = "401",
            description = "Unauthorized",
            content = [Content(schema = Schema(implementation = MessageException::class))]
        ), ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = [Content(schema = Schema(implementation = MessageException::class))]
        ), ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = [Content(schema = Schema(implementation = MessageException::class))]
        )]
    )
    fun delete(@PathVariable id: Int): ResponseEntity<Unit>

}
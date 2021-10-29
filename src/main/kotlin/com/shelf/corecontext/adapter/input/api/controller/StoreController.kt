package com.shelf.corecontext.adapter.input.api.controller

import com.shelf.corecontext.adapter.input.api.advice.MessageException
import com.shelf.corecontext.adapter.input.api.controller.model.request.PostStoreRequestModel
import com.shelf.corecontext.adapter.input.api.controller.model.request.PutStoreRequestModel
import com.shelf.corecontext.adapter.input.api.controller.model.response.StoreResponseModel
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

interface StoreController{
    companion object {
        private const val apiDescription: String = "API Store version 1.0.0"
    }

    @Operation(summary = "Find all Store", description = "", tags = [Companion.apiDescription])
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
    fun findAll(page: Int, limit: Int, direction: String?): ResponseEntity<List<StoreResponseModel>>

    @Operation(summary = "Find Store by identification", description = "", tags = [Companion.apiDescription])
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
    fun findById(id: Int): ResponseEntity<StoreResponseModel>

    @Operation(summary = "Insert a Store", description = "", tags = [Companion.apiDescription])
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
    fun insert( model: PostStoreRequestModel): ResponseEntity<StoreResponseModel>

    @Operation(summary = "Update a Store", description = "", tags = [Companion.apiDescription])
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
    fun update(id: Int, model: PutStoreRequestModel): ResponseEntity<StoreResponseModel>

    @Operation(summary = "Remove a Store", description = "", tags = [Companion.apiDescription])
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
    fun delete(id: Int): ResponseEntity<Unit>

}
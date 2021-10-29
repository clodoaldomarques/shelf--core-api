package com.shelf.corecontext.adapter.input.api.controller

import com.shelf.corecontext.adapter.input.api.advice.MessageException
import com.shelf.corecontext.adapter.input.api.controller.model.request.PostMeasureRequestModel
import com.shelf.corecontext.adapter.input.api.controller.model.request.PutMeasureRequestModel
import com.shelf.corecontext.adapter.input.api.controller.model.response.MeasureResponseModel
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

interface MeasureController{
    companion object {
        private const val apiDescription: String = "API Measure version 1.0.0"
    }

    @Operation(summary = "Find all Measure", description = "", tags = [Companion.apiDescription])
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
    fun findAll(page: Int, limit: Int, direction: String?): ResponseEntity<List<MeasureResponseModel>>

    @Operation(summary = "Find Measure by identification", description = "", tags = [Companion.apiDescription])
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
    fun findById(id: Int): ResponseEntity<MeasureResponseModel>

    @Operation(summary = "Insert a Measure", description = "", tags = [Companion.apiDescription])
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
    fun insert( model: PostMeasureRequestModel): ResponseEntity<MeasureResponseModel>

    @Operation(summary = "Update a Measure", description = "", tags = [Companion.apiDescription])
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
    fun update(id: Int, model: PutMeasureRequestModel): ResponseEntity<MeasureResponseModel>

    @Operation(summary = "Remove a Measure", description = "", tags = [Companion.apiDescription])
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
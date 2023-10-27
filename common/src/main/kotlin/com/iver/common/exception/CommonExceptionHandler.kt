package com.iver.common.exception

import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
@Order(1)
class CommonExceptionHandler {

    @ExceptionHandler
    fun handleNotFound(e: NotFoundException): ResponseEntity<ErrorResponseWrapper> =
        ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                ErrorResponseWrapper(
                    status = HTTPErrorResponseStatus.NOT_FOUND,
                    description = e.message ?: "Resource not found"
                )
            )

    @ExceptionHandler
    fun handleMethodArgumentNotValid(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponseWrapper> =
        ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                ErrorResponseWrapper(
                    status = HTTPErrorResponseStatus.BAD_REQUEST,
                    description = e.message
                )
            )
}

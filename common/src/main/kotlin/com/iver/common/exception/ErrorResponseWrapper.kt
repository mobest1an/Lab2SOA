package com.iver.common.exception

enum class HTTPErrorResponseStatus {
    NOT_FOUND,
    BAD_REQUEST,
}

class ErrorResponseWrapper(
    val status: HTTPErrorResponseStatus,
    val description: String
)

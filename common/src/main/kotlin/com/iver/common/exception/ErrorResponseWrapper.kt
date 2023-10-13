package com.iver.common.exception

enum class HTTPErrorResponseStatus {
    NOT_FOUND,
}

class ErrorResponseWrapper(
    val status: HTTPErrorResponseStatus,
    val description: String
)

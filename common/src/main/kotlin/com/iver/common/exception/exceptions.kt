package com.iver.common.exception

class NotFoundException(msg: String) : RuntimeException(msg) {
    constructor() : this("")
}

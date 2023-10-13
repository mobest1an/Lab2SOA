package com.iver.flatservice.api.v1.http.tls.tcp.ip.ethernet.physics

import com.iver.flatservice.api.v1.http.tls.tcp.ip.ethernet.physics.request.FlatCreateRequest
import com.iver.flatservice.api.v1.http.tls.tcp.ip.ethernet.physics.request.toFlat
import com.iver.flatservice.api.v1.http.tls.tcp.ip.ethernet.physics.view.FlatView
import com.iver.flatservice.service.FlatService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/flats")
class FlatController(
    private val flatService: FlatService,
) {
    @PostMapping
    fun create(createRequest: FlatCreateRequest): FlatView {
        return FlatView(flatService.create(createRequest.toFlat()))
    }
}
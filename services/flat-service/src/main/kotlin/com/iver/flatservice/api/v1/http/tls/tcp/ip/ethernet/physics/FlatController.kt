package com.iver.flatservice.api.v1.http.tls.tcp.ip.ethernet.physics

import com.iver.common.model.FlatId
import com.iver.flatservice.api.v1.http.tls.tcp.ip.ethernet.physics.requests.FlatRequest
import com.iver.flatservice.api.v1.http.tls.tcp.ip.ethernet.physics.views.FlatView
import com.iver.flatservice.api.v1.http.tls.tcp.ip.ethernet.physics.views.FlatsRepresentation
import com.iver.flatservice.api.v1.http.tls.tcp.ip.ethernet.physics.views.pageToRepresentation
import com.iver.flatservice.service.FlatService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/flats")
class FlatController(
    private val flatService: FlatService,
) {

    @GetMapping("/{id}")
    fun getFlatById(@PathVariable id: FlatId): FlatView {
        return FlatView(flatService.getFlatById(id))
    }

    @PostMapping
    fun createFlat(@RequestBody flatRequest: FlatRequest): FlatView {
        return FlatView(flatService.createFlat(flatRequest))
    }

    @PutMapping("/{id}")
    fun updateFlat(@PathVariable id: FlatId, @RequestBody flatRequest: FlatRequest): FlatView {
        return FlatView(flatService.updateFlat(id, flatRequest))
    }

    @DeleteMapping("/{id}")
    fun deleteFlat(@PathVariable id: FlatId) {
        flatService.deleteFlat(id)
    }

    @GetMapping
    fun getAllFlats(page: Int?, size: Int?): FlatsRepresentation {
        if (page == null || size == null) {
            return FlatsRepresentation(
                flatService.getAllFlats().map {
                    FlatView(it)
                },
                0,
                0
            )
        }

        return pageToRepresentation(flatService.getAllFlatsPageable(
            page = page,
            size = size,
        ).map {
            FlatView(it)
        })
    }
}

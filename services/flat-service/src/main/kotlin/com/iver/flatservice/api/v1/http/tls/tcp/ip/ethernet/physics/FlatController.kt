package com.iver.flatservice.api.v1.http.tls.tcp.ip.ethernet.physics

import com.iver.flatejb.model.Flat
import com.iver.flatejb.model.FlatId
import com.iver.flatejb.model.FlatsRepresentation
import com.iver.flatejb.utils.SortingParameters
import com.iver.flatservice.api.v1.http.tls.tcp.ip.ethernet.physics.requests.FlatRequest
import com.iver.flatservice.api.v1.http.tls.tcp.ip.ethernet.physics.views.FlatView
import com.iver.flatservice.service.FlatService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/flats")
class FlatController(
    private val flatService: FlatService,
) {

    @CrossOrigin
    @GetMapping("/{id}")
    fun getFlatById(@PathVariable id: FlatId): FlatView {
        return FlatView(flatService.getFlatById(id))
    }

    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createFlat(@Valid @RequestBody flatRequest: FlatRequest): FlatView {
        return FlatView(flatService.createFlat(flatRequest))
    }

    @CrossOrigin
    @PutMapping("/{id}")
    fun updateFlat(@PathVariable id: FlatId, @Valid @RequestBody flatRequest: FlatRequest): FlatView {
        return FlatView(flatService.updateFlat(id, flatRequest))
    }


    @CrossOrigin
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteFlat(@PathVariable id: FlatId) {
        flatService.deleteFlat(id)
    }

    @PostMapping("/delete-one-by-transport/{transport}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteByTransport(@PathVariable transport: String) {
        flatService.deleteByTransport(transport)
    }

    @CrossOrigin
    @PostMapping("/all-number-of-rooms-sum")
    fun calculateSumOfRoomNumbers(): Long {
        return flatService.calculateSumOfRoomNumbers()
    }

    @CrossOrigin
    @PostMapping("/by-start-sub-name/{sub-name}")
    fun getFlatsNameStartsWith(@PathVariable("sub-name") subName: String): List<Flat> {
        return flatService.getFlatsNameStartsWith(subName)
    }

    @CrossOrigin
    @GetMapping
    fun getAllFlats(page: Int?, size: Int?, sort: SortingParameters? = null, filters: Array<String>?, sorting: String?): FlatsRepresentation {
        if (page == null || size == null) {
            if (filters != null) {

                return FlatsRepresentation(
                    flatService.getAllFlatsBySearchCriteria(
                        filters,
                        sort
                    ).map { com.iver.flatejb.model.FlatView(it) },
                    0,
                    0
                )
            }

            return FlatsRepresentation(
                flatService.getAllFlats(sorting).map {
                    com.iver.flatejb.model.FlatView(it)
                },
                0,
                0
            )
        }

        if (!filters.isNullOrEmpty()) {
            return flatService.getAllFlatsBySearchCriteriaPageable(
                filters,
                page,
                size,
                sort,
            )
        }

        return flatService.getAllFlatsPageable(
            page = page,
            size = size,
            sorting,
        )
    }
}

package com.iver.flatservice.service

import com.iver.flatejb.model.*
import com.iver.flatejb.service.FlatService
import com.iver.flatejb.utils.SortingParameters
import com.iver.flatservice.api.v1.http.tls.tcp.ip.ethernet.physics.requests.FlatRequest
import com.iver.flatservice.api.v1.http.tls.tcp.ip.ethernet.physics.requests.toFlat
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class FlatService(
    private val flatService: FlatService
) {

    fun getFlatById(flatId: Long): Flat {
        return flatService.getFlatById(flatId)
    }

    fun createFlat(request: FlatRequest): Flat {
        return flatService.createFlat(request.toFlat())
    }

    fun updateFlat(flatId: FlatId, request: FlatRequest): Flat {
        return flatService.updateFlat(request.toFlat(flatId))
    }

    fun deleteFlat(flatId: FlatId) {
        flatService.deleteFlat(flatId)
    }

    fun deleteByTransport(transportCode: String) {
        flatService.deleteByTransport(transportCode)
    }

    fun calculateSumOfRoomNumbers(): Long {
        return flatService.calculateSumOfRoomNumbers()
    }

    fun getFlatsNameStartsWith(subName: String): List<Flat> {
        return flatService.getFlatsNameStartsWith(subName).flats.map {
            Flat(
                it.id,
                it.name,
                it.coordinates,
                it.creationDate,
                it.area,
                it.numberOfRooms,
                it.furnish,
                it.view,
                it.transport,
                it.house,
                it.cost,
            )
        }
    }

    fun getAllFlats(sort: String?): List<Flat> {
        return flatService.getAllFlats(sort).flats.map {
            Flat(
                it.id,
                it.name,
                it.coordinates,
                it.creationDate,
                it.area,
                it.numberOfRooms,
                it.furnish,
                it.view,
                it.transport,
                it.house,
                it.cost,
            )
        }
    }

    fun getAllFlatsPageable(page: Int, size: Int, sort: String?): FlatsRepresentation {
        return flatService.getAllFlatsPageable(page, size, sort)
    }

    fun getAllFlatsBySearchCriteria(
        filters: Array<String>, sort: SortingParameters?
    ): List<Flat> {
        return flatService.getAllFlatsBySearchCriteria(filters, sort)
    }

    fun getAllFlatsBySearchCriteriaPageable(
        filters: Array<String>,
        page: Int, size: Int, sort: SortingParameters?
    ): FlatsRepresentation {
        return flatService.getAllFlatsBySearchCriteriaPageable(filters, sort, page, size)
    }
}

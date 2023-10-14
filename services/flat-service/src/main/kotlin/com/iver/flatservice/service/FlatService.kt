package com.iver.flatservice.service

import com.iver.common.exception.NotFoundException
import com.iver.common.model.*
import com.iver.common.utils.checkAscOrDesc
import com.iver.flatservice.api.v1.http.tls.tcp.ip.ethernet.physics.requests.FlatRequest
import com.iver.flatservice.api.v1.http.tls.tcp.ip.ethernet.physics.requests.toFlat
import com.iver.flatservice.dao.CoordinatesRepository
import com.iver.flatservice.dao.FlatCriteriaRepository
import com.iver.flatservice.dao.FlatRepository
import com.iver.flatservice.dao.HouseRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class FlatService(
    private val flatRepository: FlatRepository,
    private val flatCriteriaRepository: FlatCriteriaRepository,
    private val coordinatesRepository: CoordinatesRepository,
    private val houseRepository: HouseRepository,
) {

    fun getFlatById(flatId: Long): Flat {
        return flatRepository.findById(flatId).orElseThrow { NotFoundException() }
    }

    fun createFlat(request: FlatRequest): Flat {
        return save(request.toFlat())
    }

    fun updateFlat(flatId: FlatId, request: FlatRequest): Flat {
        return save(request.toFlat(flatId))
    }

    fun deleteFlat(flatId: FlatId) {
        flatRepository.deleteById(flatId)
    }

    fun deleteByTransport(transportCode: String) {
        val transport = Transport.valueOf(transportCode)
        val flat = flatRepository.findFirstByTransport(transport)
        deleteFlat(flat.id)
    }

    fun calculateSumOfRoomNumbers(): Long {
        val flats = getAllFlats(null)
        var sum = 0L
        for (flat in flats) {
            sum += flat.numberOfRooms
        }

        return sum
    }

    fun getFlatsNameStartsWith(subName: String): List<Flat> {
        return flatRepository.findByNameStartingWith(subName)
    }

    fun getAllFlats(sort: String?): List<Flat> {
        return flatRepository.findAll(checkAscOrDesc(sort ?: "id")).toList()
    }

    fun getAllFlatsPageable(page: Int, size: Int, sort: String?): Page<Flat> {
        return flatRepository.findAll(
            pageable = PageRequest.of(
                page,
                size,
                checkAscOrDesc(sort ?: "id")
            )
        )
    }

    fun getAllFlatsBySearchCriteria(
        spec: Specification<Flat>, sort: String?
    ): List<Flat> {
        return flatCriteriaRepository.findAll(
            spec,
            checkAscOrDesc(sort ?: "id"),
        )
    }

    fun getAllFlatsBySearchCriteriaPageable(
        spec: Specification<Flat>,
        page: Int, size: Int, sort: String?
    ): Page<Flat> {
        return flatCriteriaRepository.findAll(
            spec,
            PageRequest.of(
                page,
                size,
                checkAscOrDesc(sort ?: "id")
            )
        )
    }

    private fun save(flat: Flat): Flat {
        saveCoordinates(flat.coordinates)
        saveHouse(flat.house)
        return saveFlat(flat)
    }

    private fun saveFlat(flat: Flat): Flat {
        return flatRepository.save(flat)
    }

    private fun saveCoordinates(coordinates: Coordinates): Coordinates {
        return coordinatesRepository.save(coordinates)
    }

    private fun saveHouse(house: House): House {
        return houseRepository.save(house)
    }
}

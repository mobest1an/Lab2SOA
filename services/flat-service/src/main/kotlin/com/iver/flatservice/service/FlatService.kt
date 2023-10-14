package com.iver.flatservice.service

import com.iver.common.exception.NotFoundException
import com.iver.common.model.Coordinates
import com.iver.common.model.Flat
import com.iver.common.model.FlatId
import com.iver.common.model.House
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

    fun getAllFlats(sort: String?): List<Flat> {
        return flatRepository.findAll(checkAscOrDesc(sort ?: "id")).toList()
    }

    fun getAllFlatsPageable(page: Int, size: Int, sort: String?): Page<Flat> {
        return flatRepository.findAll(
            pageable = PageRequest.of(
                page,
                size,
                Sort.by(sort ?: "id")
            )
        )
    }

    fun getAllFlatsBySearchCriteria(
        spec: Specification<Flat>,
    ): List<Flat> {
        return flatCriteriaRepository.findAll(spec)
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

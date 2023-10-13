package com.iver.flatservice.service

import com.iver.common.model.Flat
import com.iver.flatservice.repo.FlatRepository
import org.springframework.stereotype.Service

@Service
class FlatService(
    private val flatRepository: FlatRepository,
) {
    fun create(flat: Flat): Flat {
        return flatRepository.save(flat)
    }
}
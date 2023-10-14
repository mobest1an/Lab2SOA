package com.iver.flatservice.dao;

import com.iver.common.model.Coordinates
import com.iver.common.model.CoordinatesId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository;

@Repository
interface CoordinatesRepository: CrudRepository<Coordinates, CoordinatesId> {
}

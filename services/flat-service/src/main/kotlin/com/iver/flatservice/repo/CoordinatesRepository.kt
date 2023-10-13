package com.iver.flatservice.repo;

import com.iver.flatservice.model.Coordinates
import com.iver.flatservice.model.CoordinatesId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinatesRepository: CrudRepository<Coordinates, CoordinatesId> {
}

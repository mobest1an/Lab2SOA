package com.iver.flatservice.repo

import com.iver.flatservice.model.House
import com.iver.flatservice.model.HouseID
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface HouseRepository : CrudRepository<House, HouseID> {
}
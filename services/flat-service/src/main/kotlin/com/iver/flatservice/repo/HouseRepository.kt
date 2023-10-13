package com.iver.flatservice.repo

import com.iver.common.model.House
import com.iver.common.model.HouseID
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface HouseRepository : CrudRepository<House, HouseID> {
}
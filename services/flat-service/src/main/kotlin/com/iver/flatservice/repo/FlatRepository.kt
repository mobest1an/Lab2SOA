package com.iver.flatservice.repo

import com.iver.common.model.Flat
import com.iver.common.model.FlatId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FlatRepository : CrudRepository<Flat, FlatId>
package com.iver.flatservice.repo

import com.iver.flatservice.model.Flat
import com.iver.flatservice.model.FlatId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FlatRepository : CrudRepository<Flat, FlatId>
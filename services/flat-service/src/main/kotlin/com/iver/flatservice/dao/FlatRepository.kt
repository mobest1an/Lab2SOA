package com.iver.flatservice.dao

import com.iver.common.model.Flat
import com.iver.common.model.FlatId
import org.hibernate.Filter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FlatRepository : CrudRepository<Flat, FlatId> {

    fun findAll(
        pageable: Pageable
    ): Page<Flat>

    fun findAll(
        sort: Sort,
    ): List<Flat>
}

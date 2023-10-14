package com.iver.flatservice.dao

import com.iver.common.model.Flat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface FlatCriteriaRepository : JpaRepository<Flat, Long>, JpaSpecificationExecutor<Flat>

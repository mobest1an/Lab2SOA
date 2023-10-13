package com.iver.common.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


typealias CoordinatesId = Long

@Entity
data class Coordinates(
    @Id
    @GeneratedValue
    private val id: CoordinatesId,
    private val x: Int, //Максимальное значение поля: 235, Поле не может быть null
    private val y: Long, //Поле не может быть null
)
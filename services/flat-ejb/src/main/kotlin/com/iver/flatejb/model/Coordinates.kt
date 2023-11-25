package com.iver.flatejb.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


typealias CoordinatesId = Long

@Entity
data class Coordinates(
    @Id
    @GeneratedValue
    val id: CoordinatesId,
    val x: Int, //Максимальное значение поля: 235, Поле не может быть null
    val y: Long, //Поле не может быть null
)

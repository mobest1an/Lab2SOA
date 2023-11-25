package com.iver.flatejb.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.io.Serializable


typealias CoordinatesId = Long

@Entity
data class Coordinates(
    @Id
    @GeneratedValue
    val id: CoordinatesId,
    val x: Int, //Максимальное значение поля: 235, Поле не может быть null
    val y: Long, //Поле не может быть null
) : Serializable

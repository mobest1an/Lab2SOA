package com.iver.flatejb.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

typealias HouseID = Long

@Entity
class House(
    @Id
    @GeneratedValue
    val id: HouseID,
    val name: String, //Поле может быть null
    val year: Int, //Поле не может быть null, Значение поля должно быть больше 0
    val numberOfFlatsOnFloor: Int, //Значение поля должно быть больше 0
)

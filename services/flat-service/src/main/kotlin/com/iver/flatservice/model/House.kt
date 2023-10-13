package com.iver.flatservice.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

typealias HouseID = Long

@Entity
class House(
    @Id
    @GeneratedValue
    private val id: HouseID,
    private val name: String, //Поле может быть null
    private val year: Int, //Поле не может быть null, Значение поля должно быть больше 0
    private val numberOfFlatsOnFloor: Int, //Значение поля должно быть больше 0
)
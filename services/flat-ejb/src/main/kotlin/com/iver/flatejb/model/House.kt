package com.iver.flatejb.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.io.Serializable

typealias HouseID = Long

@Entity
class House(
    @Id
    @GeneratedValue
    val id: HouseID,
    val name: String, //Поле может быть null
    val year: Int, //Поле не может быть null, Значение поля должно быть больше 0
    @Column(name="number_of_flats_on_floor")
    val numberOfFlatsOnFloor: Int, //Значение поля должно быть больше 0
) : Serializable

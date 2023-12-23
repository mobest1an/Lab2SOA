package com.iver.flatejb.model

import java.util.*
import jakarta.persistence.*
import java.io.Serializable

typealias FlatId = Long

@Entity
@Table(name = "flat")
data class Flat(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: FlatId, //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    val name: String, //Поле не может быть null, Строка не может быть пустой
    @OneToOne(fetch = FetchType.EAGER)
    val coordinates: Coordinates, //Поле не может быть null
//    @Column(name = "creation_date")
    val creationDate: Date, //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    val area: Int, //Максимальное значение поля: 700, Значение поля должно быть больше 0
    @Column(name="number_of_rooms")
    val numberOfRooms: Int, //Значение поля должно быть больше 0
    val furnish: Furnish, //Поле может быть null
    val view: View, //Поле не может быть null
    val transport: Transport, //Поле может быть null
    @OneToOne(fetch = FetchType.EAGER)
    val house: House, //Поле не может быть null
    val cost: Double,
): Serializable

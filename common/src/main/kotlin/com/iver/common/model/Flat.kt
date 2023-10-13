package com.iver.common.model

import java.util.*
import javax.persistence.*

typealias FlatId = Long

@Entity
@Table(name = "flat")
data class Flat(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: FlatId, //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private val name: String, //Поле не может быть null, Строка не может быть пустой
    private val coordinates: Coordinates, //Поле не может быть null
    private val creationDate: Date, //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private val area: Int, //Максимальное значение поля: 700, Значение поля должно быть больше 0
    private val numberOfRooms: Int, //Значение поля должно быть больше 0
    private val furnish: Furnish, //Поле может быть null
    private val view: View, //Поле не может быть null
    private val transport: Transport, //Поле может быть null
    private val house: House, //Поле не может быть null
)
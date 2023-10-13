package com.iver.flatservice.api.v1.http.tls.tcp.ip.ethernet.physics.view

import com.iver.flatservice.model.*
import java.util.*

class FlatView(
    val id: FlatId, //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    val name: String, //Поле не может быть null, Строка не может быть пустой
    val coordinates: Coordinates, //Поле не может быть null
    val creationDate: Date, //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    val area: Int, //Максимальное значение поля: 700, Значение поля должно быть больше 0
    val numberOfRooms: Int, //Значение поля должно быть больше 0
    val furnish: Furnish, //Поле может быть null
    val view: View, //Поле не может быть null
    val transport: Transport, //Поле может быть null
    val house: House, //Поле не может быть null
) {
    constructor(flat: Flat) : this(
        flat.id,
        flat.name,
        flat.coordinates,
        flat.creationDate,
        flat.area,
        flat.numberOfRooms,
        flat.furnish,
        flat.view,
        flat.transport,
        flat.house,
    )
}
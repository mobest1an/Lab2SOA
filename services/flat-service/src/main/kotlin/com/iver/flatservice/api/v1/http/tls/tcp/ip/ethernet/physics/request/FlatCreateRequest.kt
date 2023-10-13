package com.iver.flatservice.api.v1.http.tls.tcp.ip.ethernet.physics.request

import com.iver.flatservice.model.*
import java.util.*

data class FlatCreateRequest(
    val name: String, //Поле не может быть null, Строка не может быть пустой
    val coordinates: Coordinates, //Поле не может быть null
    val creationDate: Date, //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    val area: Int, //Максимальное значение поля: 700, Значение поля должно быть больше 0
    val numberOfRooms: Int, //Значение поля должно быть больше 0
    val furnish: Furnish, //Поле может быть null
    val view: View, //Поле не может быть null
    val transport: Transport, //Поле может быть null
    val house: House, //Поле не может быть null
)

fun FlatCreateRequest.toFlat(): Flat {
    return Flat(
        id = 0,
        name, coordinates, creationDate, area, numberOfRooms, furnish, view, transport, house
    )
}

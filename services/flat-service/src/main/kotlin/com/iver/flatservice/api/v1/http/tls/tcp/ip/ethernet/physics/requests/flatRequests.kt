package com.iver.flatservice.api.v1.http.tls.tcp.ip.ethernet.physics.requests

import com.iver.common.model.*
import java.util.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class FlatRequest(
    @NotBlank(message = "Name is mandatory")
    val name: String, //Поле не может быть null, Строка не может быть пустой
    val coordinates: Coordinates, //Поле не может быть null
    @Max(700, message = "Area max value is 700")
    val area: Int, //Максимальное значение поля: 700, Значение поля должно быть больше 0
    @Min(1, message = "Number of rooms cannot be less than 1")
    val numberOfRooms: Int, //Значение поля должно быть больше 0
    val furnish: Furnish, //Поле может быть null
    val view: View, //Поле не может быть null
    val transport: Transport, //Поле может быть null
    val house: House, //Поле не может быть null
    val cost: Double,
)

fun FlatRequest.toFlat(flatId: FlatId = 0): Flat {
    return Flat(
        id = flatId,
        name, coordinates, Date(), area, numberOfRooms, furnish, view, transport, house, cost
    )
}

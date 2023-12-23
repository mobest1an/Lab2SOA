package com.iver.agencyprovider.api.v1.http.views


import com.fasterxml.jackson.annotation.JsonProperty
import com.iver.common.model.*
import java.util.*

class FlatView(
    @JsonProperty("id")
    val id: FlatId, //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @JsonProperty("name")
    val name: String, //Поле не может быть null, Строка не может быть пустой
    @JsonProperty("coordinates")
    val coordinates: Coordinates, //Поле не может быть null
    @JsonProperty("creationDate")
    val creationDate: Date, //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @JsonProperty("area")
    val area: Int, //Максимальное значение поля: 700, Значение поля должно быть больше 0
    @JsonProperty("numberOfRooms")
    val numberOfRooms: Int, //Значение поля должно быть больше 0
    @JsonProperty("furnish")
    val furnish: Furnish, //Поле может быть null
    @JsonProperty("view")
    val view: View, //Поле не может быть null
    @JsonProperty("transport")
    val transport: Transport, //Поле может быть null
    @JsonProperty("house")
    val house: House, //Поле не может быть null
    @JsonProperty("cost")
    val cost: Double
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
        flat.cost,
    )
}

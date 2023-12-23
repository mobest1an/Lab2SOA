package com.iver.agencyservice.api.v1.http.soap

import com.iver.agencyservice.service.AgencyService
import org.springframework.stereotype.Service
import org.springframework.ws.server.endpoint.annotation.Endpoint
import org.springframework.ws.server.endpoint.annotation.PayloadRoot
import org.springframework.ws.server.endpoint.annotation.RequestPayload
import org.springframework.ws.server.endpoint.annotation.ResponsePayload
import java.util.*
import javax.xml.datatype.DatatypeFactory


@Service
@Endpoint
class AgencyEndpointImpl(
    private val agencyService: AgencyService,
) {

    @PayloadRoot(namespace = "http://iver.com", localPart = "getCheapestFlatRequest")
    @ResponsePayload
    fun getCheapestFlat(@RequestPayload getCheapestFlatRequest: GetCheapestFlatRequest): GetCheapestFlatResponse {
//        val res = agencyService.getCheapestFlat(getCheapestFlatRequest.id1!!, getCheapestFlatRequest.id2!!)
        val res = agencyService.getCheapestFlat(1,2)
        val response = GetCheapestFlatResponse()
        response.id = res.id
        response.name = res.name
        response.coordinatesId = res.coordinates.id
        response.coordinatesX = res.coordinates.x
        response.coordinatesY = res.coordinates.y
        response.creationDate = res.let {
            val c = GregorianCalendar()
            c.time = it.creationDate
            DatatypeFactory.newInstance().newXMLGregorianCalendar(c)
        }
        response.area = res.area
        response.numberOfRooms = res.numberOfRooms
        response.furnish = res.furnish.name
        response.view = res.view.name
        response.transport = res.transport.name
        response.houseId = res.house.id
        response.houseName = res.house.name
        response.houseNumberOfFlatsOnFloor = res.house.numberOfFlatsOnFloor
        response.cost = res.cost

        return response
    }

    @PayloadRoot(namespace = "http://iver.com", localPart = "getTotalCostRequest")
    @ResponsePayload
    fun getFlatsTotalCost(): GetTotalCostResponse {
        val totalCost = GetTotalCostResponse()
        totalCost.totalCost = agencyService.getFlatsTotalCost()
        return totalCost
    }
}
package com.iver.agencyservice.api.v1.http.soap

import com.iver.agencyservice.service.AgencyService
import org.springframework.stereotype.Service
import org.springframework.ws.server.endpoint.annotation.Endpoint
import org.springframework.ws.server.endpoint.annotation.PayloadRoot
import org.springframework.ws.server.endpoint.annotation.ResponsePayload


@Service
@Endpoint
class AgencyEndpointImpl(
    private val agencyService: AgencyService,
) {

//    @Bean
//    fun messageDispatcherServlet(): MessageDispatcherServlet {
//        return MessageDispatcherServlet()
//    }

//    @PayloadRoot(namespace = "http://iver.com", localPart = "getCheapestFlatRequest")
//    @ResponsePayload
//    override fun getCheapestFlat(id1: Long, id2: Long): FlatView {
//        return agencyService.getCheapestFlat(id1, id2)
//    }

    @PayloadRoot(namespace = "http://iver.com", localPart = "getTotalCostRequest")
    @ResponsePayload
    fun getFlatsTotalCost(): GetTotalCostResponse {
        val totalCost = GetTotalCostResponse()
        totalCost.totalCost = agencyService.getFlatsTotalCost()
        return totalCost
    }
}
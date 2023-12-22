package com.iver.agencyservice.api.v1.http.soap

import com.iver.agencyservice.service.AgencyService
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import org.springframework.ws.server.endpoint.annotation.Endpoint
import org.springframework.ws.server.endpoint.annotation.PayloadRoot
import org.springframework.ws.server.endpoint.annotation.ResponsePayload
import org.springframework.ws.transport.http.MessageDispatcherServlet
import com.iver.*


@Service
@Endpoint
class AgencyServiceEndpointImpl(
    private val agencyService: AgencyService,
) : AgencyServiceEndpoint {

    @Bean
    fun messageDispatcherServlet(): MessageDispatcherServlet {
        return MessageDispatcherServlet()
    }

//    @PayloadRoot(namespace = "http://iver.com", localPart = "getCheapestFlatRequest")
//    @ResponsePayload
//    override fun getCheapestFlat(id1: Long, id2: Long): FlatView {
//        return agencyService.getCheapestFlat(id1, id2)
//    }

    @PayloadRoot(namespace = "http://iver.com", localPart = "getTotalCostRequest")
    @ResponsePayload
    override fun getFlatsTotalCost(): GetTotalCostResponse {
        return GetTotalCostResponse(agencyService.getFlatsTotalCost())
    }
}
package com.iver.agencyprovider.integration.agency.provider

import com.iver.agencyprovider.api.v1.http.views.FlatView
import com.iver.common.exception.NotFoundException
import org.springframework.stereotype.Service


@Service
class AgencyProviderService(
    private val countryClient: CountryClient,
) {

//    private val webServiceClient = WebServiceClient("http://localhost:8081/ws")

    fun getCheapestFlat(id1: Long, id2: Long): FlatView {

        TODO()
    }

    fun getFlatsTotalCost(): Double {
//        val restTemplate = RestTemplate()
////        val messageConverters: MutableList<HttpMessageConverter<*>> = ArrayList()
////        val converter = MappingJackson2HttpMessageConverter()
////        converter.supportedMediaTypes = Collections.singletonList(MediaType.TEXT_XML)
////        messageConverters.add(converter)
////        restTemplate.messageConverters = messageConverters
//        val headers = HttpHeaders()
//        headers.contentType = MediaType.TEXT_XML
//        val request = HttpEntity<String>(getTotalCostMessage(), headers)
//
//        val result = restTemplate.postForObject("http://localhost:8081/ws", request, Any::class.java)

        val totalCostResponse = countryClient.getCountry()

        return totalCostResponse.totalCost ?: throw  NotFoundException("PIZDA")
    }
}

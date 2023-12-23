package com.iver.agencyprovider.integration.agency.provider

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.oxm.jaxb.Jaxb2Marshaller
import org.springframework.ws.client.core.support.WebServiceGatewaySupport
import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType


class CountryClient : WebServiceGatewaySupport() {
    fun getCountry(): GetTotalCostResponse {
        val request = GetTotalCostRequest()

        return webServiceTemplate
            .marshalSendAndReceive(request) as GetTotalCostResponse
    }
}

@Configuration
class CountryClientConfig {
    @Bean
    fun marshaller(): Jaxb2Marshaller {
        val marshaller = Jaxb2Marshaller()
        marshaller.setClassesToBeBound(GetTotalCostRequest::class.java, GetTotalCostResponse::class.java)
        return marshaller
    }

    @Bean
    fun countryClient(marshaller: Jaxb2Marshaller): CountryClient {
        val client = CountryClient()
        client.defaultUri = "http://localhost:8081/ws"
        client.marshaller = marshaller
        client.unmarshaller = marshaller
        return client
    }
}

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "", propOrder = [
        "totalCost"
    ]
)
@XmlRootElement(name = "getTotalCostResponse")
class GetTotalCostResponse {
    var totalCost: Double? = null
}

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "getTotalCostRequest", namespace = "http://iver.com")
class GetTotalCostRequest

package com.iver.agencyprovider.integration.agency.provider

import com.iver.agencyprovider.api.v1.http.views.FlatView
import org.springframework.stereotype.Service
import org.springframework.ws.client.core.WebServiceTemplate
import java.io.StringReader
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource


@Service
class AgencyProviderService {

    private val webServiceClient = WebServiceClient("http://localhost:8081/ws")

    fun getCheapestFlat(id1: Long, id2: Long): FlatView {
        webServiceClient.simpleSendAndReceive(getCheapestMessage(id1, id2))
    }

    fun getFlatsTotalCost(): Double {
        webServiceClient.simpleSendAndReceive(getTotalCostMessage())
    }


    fun getTotalCostMessage(): String {
        return "<?xml version=\"1.0\"?>\n" +
                "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <SOAP-ENV:Header/>\n" +
                "  <SOAP-ENV:Body>\n" +
                "    <ns2:getTotalCostRequest xmlns:ns2=\"http://iver.com\">\n" +
                "    </ns2:getTotalCostRequest>\n" +
                "  </SOAP-ENV:Body>\n" +
                "</SOAP-ENV:Envelope>"
    }

    fun getCheapestMessage(id1: Long, id2: Long): String {
        return "<?xml version=\"1.0\"?>\n" +
                "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns2=\"http://iver.com\">\n" +
                "  <SOAP-ENV:Header/>\n" +
                "  <SOAP-ENV:Body>\n" +
                "    <ns2:getCheapestFlatRequest>\n" +
                "    <ns2:country>\n" +
                "        <ns2:id1>$id1</ns2:id1>\n" +
                "        <ns2:id2>$id2</ns2:id2>\n" +
                "      </ns2:country>\n" +
                "    </ns2:getCheapestFlatRequest>\n" +
                "  </SOAP-ENV:Body>\n" +
                "</SOAP-ENV:Envelope>"
    }
}

class WebServiceClient(
    defaultUri: String
) {
    private val webServiceTemplate = WebServiceTemplate()

    init {
        webServiceTemplate.defaultUri = defaultUri
    }

    fun simpleSendAndReceive(message: String) {
        val source = StreamSource(StringReader(message))
        val result = StreamResult(System.out)
        webServiceTemplate.sendSourceAndReceiveToResult(source, result)
    }
}

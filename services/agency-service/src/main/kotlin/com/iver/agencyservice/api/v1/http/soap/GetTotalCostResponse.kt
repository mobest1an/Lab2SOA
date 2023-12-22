package com.iver.agencyservice.api.v1.http.soap;

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType

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

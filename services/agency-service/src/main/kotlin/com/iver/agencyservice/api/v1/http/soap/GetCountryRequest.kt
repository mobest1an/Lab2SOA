package com.iver.agencyservice.api.v1.http.soap;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "", propOrder = ["name"
    ]
)
@XmlRootElement(name = "getCountryRequest")
class GetCountryRequest {
    /**
     * Gets the value of the name property.
     *
     * @return
     * possible object is
     * [String]
     */
    /**
     * Sets the value of the name property.
     *
     * @param value
     * allowed object is
     * [String]
     */
    @XmlElement(required = true)
    var name: String? = null
}
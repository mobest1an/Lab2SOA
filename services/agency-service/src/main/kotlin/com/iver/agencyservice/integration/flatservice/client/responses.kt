package com.iver.agencyservice.integration.flatservice.client

import com.fasterxml.jackson.annotation.JsonProperty
import com.iver.agencyservice.api.v1.http.tls.ip.mac.binary.views.FlatView

class FlatsRepresentation(
    @JsonProperty("flats")
    val flats: List<FlatView>,
    @JsonProperty("elements")
    val elements: Long,
    @JsonProperty("pages")
    val pages: Int,
)

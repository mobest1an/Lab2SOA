package com.iver.agencyservice.integration.flatservice.client

import com.iver.agencyservice.api.v1.http.tls.ip.mac.binary.views.FlatView

class FlatsRepresentation(
    val flats: List<FlatView>,
    val elements: Long,
    val pages: Int,
)

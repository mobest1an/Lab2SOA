package com.iver.agencyservice.service

import com.iver.agencyservice.api.v1.http.tls.ip.mac.binary.views.FlatView
import com.iver.agencyservice.integration.flatservice.client.FlatsService
import com.iver.common.model.FlatId
import org.springframework.stereotype.Service

@Service
class AgencyService(
    private val flatsService: FlatsService,
) {

    fun getCheapestFlat(id1: FlatId, id2: FlatId): FlatView {
        val flat1 = flatsService.getFlatById(id1)
        val flat2 = flatsService.getFlatById(id2)

        if (flat1.cost < flat2.cost) {
            return flat1
        }
        return flat2
    }

    fun getFlatsTotalCost(): Double {
        val flats = flatsService.getAllFlats().flats
        var summ = 0.0
        for (flat in flats) {
            summ += flat.cost
        }

        return summ
    }
}

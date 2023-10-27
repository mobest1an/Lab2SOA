package com.iver.agencyservice.api.v1.http.tls.ip.mac.binary

import com.iver.agencyservice.api.v1.http.tls.ip.mac.binary.views.FlatView
import com.iver.agencyservice.service.AgencyService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/agency")
class AgencyController(
    private val agencyService: AgencyService,
) {

    @CrossOrigin
    @PostMapping("/get-cheapest/{id1}/{id2}")
    fun getCheapestFlat(@PathVariable id1: Long, @PathVariable id2: Long): FlatView {
        return agencyService.getCheapestFlat(id1, id2)
    }

    @CrossOrigin
    @PostMapping("/get-total-cost")
    fun getFlatsTotalCost(): Double {
        return agencyService.getFlatsTotalCost()
    }
}

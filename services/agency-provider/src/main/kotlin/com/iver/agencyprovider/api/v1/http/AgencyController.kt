package com.iver.agencyprovider.api.v1.http

import com.iver.agencyprovider.api.v1.http.views.FlatView
import com.iver.agencyprovider.integration.agency.provider.AgencyProviderService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/agency")
class AgencyController(
    private val agencyProviderService: AgencyProviderService,
) {

    @CrossOrigin
    @PostMapping("/get-cheapest/{id1}/{id2}")
    fun getCheapestFlat(@PathVariable id1: Long, @PathVariable id2: Long): FlatView {
        return agencyProviderService.getCheapestFlat(id1, id2)
    }

    @CrossOrigin
    @PostMapping("/get-total-cost")
    fun getFlatsTotalCost(): Double {
        return agencyProviderService.getFlatsTotalCost()
    }
}

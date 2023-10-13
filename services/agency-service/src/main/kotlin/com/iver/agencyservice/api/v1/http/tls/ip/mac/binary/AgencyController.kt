package com.iver.agencyservice.api.v1.http.tls.ip.mac.binary

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/agency")
class AgencyController {

    @PostMapping("/get-cheapest/{id1}/{id2}")
    fun getCheapestFlat(@PathVariable id1: Long, @PathVariable id2: Long) {

    }

    @PostMapping("/get-total-cost")
    fun getFlatsTotalCost() {

    }
}
package com.iver.agencyservice.integration.flatservice.client

import com.iver.agencyservice.api.v1.http.tls.ip.mac.binary.views.FlatView
import com.iver.common.model.Flat
import com.iver.common.model.FlatId
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FlatsAPI {

    @GET("/{id}")
    fun getFlatById(
        @Path("id") flatId: FlatId,
    ): Call<FlatView>

    @GET("")
    fun getAllFlats(): Call<FlatsRepresentation>
}

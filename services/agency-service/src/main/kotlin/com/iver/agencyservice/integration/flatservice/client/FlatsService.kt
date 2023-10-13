package com.iver.agencyservice.integration.flatservice.client

import com.iver.agencyservice.api.v1.http.tls.ip.mac.binary.views.FlatView
import com.iver.common.exception.NotFoundException
import com.iver.common.model.FlatId
import org.springframework.stereotype.Service
import retrofit2.Retrofit

@Service
class FlatsService(
    retrofit: Retrofit
) {

    private val flatServiceApi = retrofit.create(FlatsAPI::class.java)

    fun getFlatById(flatId: FlatId): FlatView {
        return flatServiceApi.getFlatById(flatId).execute().body() ?: throw NotFoundException("Flat not found")
    }

    fun getAllFlats(): FlatsRepresentation {
        return flatServiceApi.getAllFlats().execute().body() ?: throw NotFoundException("Flats don't exist")
    }
}

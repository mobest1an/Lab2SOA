package com.iver.agencyservice.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

@Configuration
class FlatServiceRetrofitConfig(
    @Value("\${flat.api.url}")
    private val flatServiceBaseUrl: String,
) {

    @Bean
    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(flatServiceBaseUrl)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
    }
}

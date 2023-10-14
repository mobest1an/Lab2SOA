package com.iver.agencyservice.config

import okhttp3.OkHttpClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


@Configuration
class FlatServiceRetrofitConfig(
    @Value("\${flat.api.url}")
    private val flatServiceBaseUrl: String,
) {

    @Bean
    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(flatServiceBaseUrl)
            .client(getTlsTrustOkHttpClient())
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
    }

    private fun getTlsTrustOkHttpClient(): OkHttpClient {
        val trustAllCerts: TrustManager = object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }

        val sslContext: SSLContext = SSLContext.getInstance("SSL")
        sslContext.init(null, arrayOf(trustAllCerts), SecureRandom())


        return OkHttpClient.Builder()
            .sslSocketFactory(sslContext.socketFactory, trustAllCerts as X509TrustManager)
            .hostnameVerifier { _, _ -> true }
            .build()
    }
}

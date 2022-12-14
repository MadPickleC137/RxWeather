package com.madpickle.core_network

import com.madpickle.core_network.server.ApiKeyInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

/**
 * Created by David Madilyan on 13.12.2022.
 */
class OkHttpClientBuilder @Inject constructor (private val apiKeyInterceptor: ApiKeyInterceptor){
    fun getHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(apiKeyInterceptor)
            .build()
    }
}
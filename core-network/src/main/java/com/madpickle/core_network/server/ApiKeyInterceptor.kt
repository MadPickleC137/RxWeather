package com.madpickle.core_network.server

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by David Madilyan on 21.08.2022.
 */
class ApiKeyInterceptor(private val apiKey: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("key", apiKey).build()
        return chain.proceed(request)
    }
}
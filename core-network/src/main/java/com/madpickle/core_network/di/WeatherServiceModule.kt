package com.madpickle.core_network.di

import com.madpickle.core_network.IWeatherNetworkSource
import com.madpickle.core_network.IWeatherService
import com.madpickle.core_network.OkHttpClientBuilder
import com.madpickle.core_network.WeatherNetworkSource
import com.madpickle.core_network.server.ApiKeyInterceptor
import com.madpickle.core_network.server.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by David Madilyan on 21.08.2022.
 */
@Module
class WeatherServiceModule {

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(): ApiKeyInterceptor {
        return ApiKeyInterceptor(Constants.API_KEY)
    }

    @Provides
    @Singleton
    fun provideIWeatherService(okHttpClient: OkHttpClientBuilder): IWeatherService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(Constants.ROOT_URL)
            .client(okHttpClient.getHttpClient())
            .build()
            .create(IWeatherService::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherNetworkSource(apiService: IWeatherService): IWeatherNetworkSource = WeatherNetworkSource(apiService)
}
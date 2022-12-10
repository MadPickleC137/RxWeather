package com.madpickle.core_network.di

import com.madpickle.core_network.IWeatherNetworkSource
import com.madpickle.core_network.IWeatherService
import com.madpickle.core_network.WeatherNetworkSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by David Madilyan on 21.08.2022.
 */
@Module
class WeatherServiceModule {

    @Provides
    @Singleton
    fun provideWeatherNetworkSource(apiService: IWeatherService): IWeatherNetworkSource = WeatherNetworkSource(apiService)
}
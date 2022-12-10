package com.madpickle.core_data.di

import com.madpickle.core_data.realm_db.*
import com.madpickle.core_data.source.*
import com.madpickle.core_network.IWeatherNetworkSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by David Madilyan on 28.08.2022.
 */
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCurrentWeatherRepo(currentWeatherDao: CurrentWeatherDao, networkSource: IWeatherNetworkSource): ICurrentWeatherRepository =
        CurrentWeatherRepository(currentWeatherDao, networkSource)

    @Provides
    @Singleton
    fun provideLocationRepo(locationDao: LocationDao, networkSource: IWeatherNetworkSource): ILocationRepository =
        LocationRepository(locationDao, networkSource)

    @Provides
    @Singleton
    fun provideForecastRepo(dayForecastDao: ForecastDao,
                              networkSource: IWeatherNetworkSource,
    ): IForecastRepository = ForecastRepository(dayForecastDao, networkSource)

    @Provides
    @Singleton
    fun provideAstronomyRepo(astronomyDao: AstronomyDao,
                              networkSource: IWeatherNetworkSource,
    ): IAstronomyRepository = AstronomyRepository(astronomyDao, networkSource)
}
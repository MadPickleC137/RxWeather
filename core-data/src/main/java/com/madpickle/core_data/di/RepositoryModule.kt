package com.madpickle.core_data.di

import com.madpickle.core_android.repository.BaseRepository
import com.madpickle.core_data.realm_db.*
import com.madpickle.core_data.repositories.*
import com.madpickle.core_network.IWeatherNetworkSource
import com.madpickle.core_network.di.WeatherServiceModule
import dagger.Module
import dagger.Provides

/**
 * Created by David Madilyan on 28.08.2022.
 */
@Module(includes = [WeatherServiceModule::class, DatabaseModule::class])
class RepositoryModule {

    @Provides
    fun provideCurrentWeatherRepo(networkSource: IWeatherNetworkSource, currentWeatherDao: CurrentWeatherDao): ICurrentWeatherRepository =
        CurrentWeatherRepository(currentWeatherDao, networkSource)

    @Provides
    fun provideLocationRepo(locationDao: LocationDao, networkSource: IWeatherNetworkSource): ILocationRepository =
        LocationRepository(locationDao, networkSource)

    @Provides
    fun provideForecastRepo(dayForecastDao: ForecastDao,
                              networkSource: IWeatherNetworkSource,
    ): IForecastRepository = ForecastRepository(dayForecastDao, networkSource)

    @Provides
    fun provideAstronomyRepo(astronomyDao: AstronomyDao,
                              networkSource: IWeatherNetworkSource,
    ): IAstronomyRepository = AstronomyRepository(astronomyDao, networkSource)
}
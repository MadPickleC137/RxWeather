package com.madpickle.core_data.di

import com.madpickle.core_data.realm_db.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by David Madilyan on 25.08.2022.
 */
@Module
class DatabaseModule {



    @Provides
    @Singleton
    fun provideCurrentWeatherDao(): CurrentWeatherDao {
        return CurrentWeatherDao()
    }

    @Provides
    @Singleton
    fun provideForecastDao(): ForecastDao {
        return ForecastDao()
    }

    @Provides
    @Singleton
    fun provideLocationDao(): LocationDao {
        return LocationDao()
    }

    @Provides
    @Singleton
    fun provideAstronomyDao(): AstronomyDao {
        return AstronomyDao()
    }
}
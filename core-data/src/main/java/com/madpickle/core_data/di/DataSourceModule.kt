package com.madpickle.core_data.di

import com.madpickle.core_data.room_db.AppDatabase
import com.madpickle.core_data.source.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by David Madilyan on 28.08.2022.
 */
@Module
class DataSourceModule {
    @Provides
    @Singleton
    fun provideCurrentDataSource(appDatabase: AppDatabase): ICurrentWeatherDataSource =
        CurrentWeatherDataSource(appDatabase.currentWeatherDao())

    @Provides
    @Singleton
    fun provideLocationDataSource(appDatabase: AppDatabase): ILocationDataSource =
        LocationDataSource(appDatabase.locationDao())

    @Provides
    @Singleton
    fun provideAlertDataSource(appDatabase: AppDatabase): IAlertDataSource =
        AlertDataSource(appDatabase.alertDao())

}
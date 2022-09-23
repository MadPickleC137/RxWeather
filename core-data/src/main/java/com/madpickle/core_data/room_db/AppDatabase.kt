package com.madpickle.core_data.room_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.madpickle.core_data.model.*
import com.madpickle.core_data.room_db.dao.*

/**
 * Created by David Madilyan on 25.08.2022.
 */
@Database(entities = [
    CurrentModel::class,
    LocationModel::class,
    AlertModel::class,
    AstronomyModel::class,
    DayForecastModel::class,
    HourForecastModel::class,
], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao
    abstract fun alertDao(): AlertDao
    abstract fun locationDao(): LocationDao
    abstract fun astronomyDao(): AstronomyDao
    abstract fun forecastDao(): ForecastWithDaysDao
}
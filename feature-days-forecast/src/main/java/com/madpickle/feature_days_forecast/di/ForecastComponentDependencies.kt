package com.madpickle.feature_days_forecast.di

import com.madpickle.core_data.repositories.AstronomyRepository
import com.madpickle.core_data.repositories.ForecastRepository

/**
 * Created by David Madilyan on 14.12.2022.
 */
interface ForecastComponentDependencies {
    fun getForecastRepo(): ForecastRepository
    fun getAstronomyRepo(): AstronomyRepository
}
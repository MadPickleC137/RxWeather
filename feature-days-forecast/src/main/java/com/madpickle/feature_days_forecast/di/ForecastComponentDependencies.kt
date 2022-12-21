package com.madpickle.feature_days_forecast.di

import com.madpickle.core_data.repositories.IAstronomyRepository
import com.madpickle.core_data.repositories.IForecastRepository

/**
 * Created by David Madilyan on 14.12.2022.
 */
interface ForecastComponentDependencies {
    fun getForecastRepo(): IForecastRepository
    fun getAstronomyRepo(): IAstronomyRepository
}
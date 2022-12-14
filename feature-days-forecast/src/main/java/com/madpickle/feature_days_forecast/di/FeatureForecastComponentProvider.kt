package com.madpickle.feature_days_forecast.di

/**
 * Created by David Madilyan on 14.12.2022.
 */
interface FeatureForecastComponentProvider {
    fun getForecastComponentDependencies(): ForecastComponentDependencies
}
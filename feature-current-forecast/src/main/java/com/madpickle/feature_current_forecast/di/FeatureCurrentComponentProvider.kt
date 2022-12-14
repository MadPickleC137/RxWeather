package com.madpickle.feature_current_forecast.di

/**
 * Created by David Madilyan on 14.12.2022.
 */
interface FeatureCurrentComponentProvider {
    fun getCurrentComponentDependencies(): CurrentsComponentDependencies
}
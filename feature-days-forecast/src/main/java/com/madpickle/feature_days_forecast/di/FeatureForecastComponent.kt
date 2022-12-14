package com.madpickle.feature_days_forecast.di

import androidx.fragment.app.Fragment
import dagger.Component

/**
 * Created by David Madilyan on 14.12.2022.
 */
@Component(dependencies = [ForecastComponentDependencies::class])
interface FeatureForecastComponent {
    fun injectRootFragment(fragment: Fragment)
}
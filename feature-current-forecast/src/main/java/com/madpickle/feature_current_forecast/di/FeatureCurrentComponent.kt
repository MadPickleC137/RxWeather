package com.madpickle.feature_current_forecast.di

import com.madpickle.feature_current_forecast.view.CurrentsFragment
import dagger.Component

/**
 * Created by David Madilyan on 14.12.2022.
 */
@Component(dependencies = [CurrentsComponentDependencies::class])
interface FeatureCurrentComponent {
    fun injectCurrentsFragment(fragment: CurrentsFragment)
}
package com.madpickle.feature_places.di

import com.madpickle.feature_places.view.AddPlaceFragment
import dagger.Component

/**
 * Created by David Madilyan on 19.12.2022.
 */
@Component(dependencies = [PlacesComponentDependencies::class])
interface FeaturePlacesComponent {
    fun injectAddPlaceFragment(fragment: AddPlaceFragment)
}
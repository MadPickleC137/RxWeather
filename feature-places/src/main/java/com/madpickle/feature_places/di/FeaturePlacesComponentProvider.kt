package com.madpickle.feature_places.di

/**
 * Created by David Madilyan on 19.12.2022.
 */
interface FeaturePlacesComponentProvider {
    fun getPlacesDependencies(): PlacesComponentDependencies
}
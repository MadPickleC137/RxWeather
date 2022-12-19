package com.madpickle.feature_places.di

import com.madpickle.core_data.repositories.LocationRepository

/**
 * Created by David Madilyan on 19.12.2022.
 */
interface PlacesComponentDependencies {
    fun getLocationRepo(): LocationRepository
}
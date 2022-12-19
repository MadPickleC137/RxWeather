package com.madpickle.feature_places.model

import com.madpickle.core_data.model.LocationModel

/**
 * Created by David Madilyan on 19.12.2022.
 */
sealed class PlacesViewState{
    object Init: PlacesViewState()
    object EmptySearch: PlacesViewState()
    object Loading: PlacesViewState()
    object SelectSuccess: PlacesViewState()
    data class Places(val places: List<LocationModel>): PlacesViewState()
}

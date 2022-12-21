package com.madpickle.feature_places.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.madpickle.core_android.viewmodel.BaseViewModel
import com.madpickle.core_data.model.LocationModel
import com.madpickle.core_data.repositories.ICurrentWeatherRepository
import com.madpickle.core_data.repositories.ILocationRepository
import com.madpickle.feature_places.model.PlacesViewState
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by David Madilyan on 17.12.2022.
 */
class PlacesViewModel @Inject constructor(private val locationRepo: ILocationRepository,
                                          private val currentRepo: ICurrentWeatherRepository
): BaseViewModel() {

    private val _state = MutableLiveData<PlacesViewState>(PlacesViewState.Init)
    val state: LiveData<PlacesViewState> = _state

    val hideKeyboard = MutableLiveData<Any>()

    fun onSearchTextChanged(text: CharSequence) {
        locationRepo.searchLocations(text.toString()).doOnSubscribe {
            _state.postValue(PlacesViewState.Loading)
        }
        .doOnError {
            Timber.e(it)
            _state.postValue(PlacesViewState.EmptySearch)
        }
        .subscribe {
            if(it.isEmpty()) _state.postValue(PlacesViewState.EmptySearch)
            else _state.postValue(PlacesViewState.Places(it))
        }.addTo(disposeBag)
    }

    fun onItemSelected(locationModel: LocationModel) {
        hideKeyboard.value = Any()
        currentRepo.getCurrentApi(locationModel.name)
            .doOnSubscribe {
                _state.postValue(PlacesViewState.Loading)
            }
            .doOnError {
                Timber.i(it)
            }
            .subscribe { model ->
                if(model.region.isNotEmpty()){
                    currentRepo.insertCurrentModel(model)
                        .doOnError {
                            Timber.i(it)
                        }
                        .subscribe {
                            _state.postValue(PlacesViewState.SelectSuccess)
                        }
                }
            }.addTo(disposeBag)
    }
}
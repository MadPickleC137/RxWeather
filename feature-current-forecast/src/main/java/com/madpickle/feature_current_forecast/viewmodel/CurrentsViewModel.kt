package com.madpickle.feature_current_forecast.viewmodel

import androidx.lifecycle.MutableLiveData
import com.madpickle.core_android.viewmodel.BaseViewModel
import com.madpickle.core_data.repositories.CurrentWeatherRepository
import com.madpickle.feature_current_forecast.model.CurrentsViewState
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by David Madilyan on 10.09.2022.
 */
class CurrentsViewModel @Inject constructor(private val repository: CurrentWeatherRepository): BaseViewModel() {
    private val _state = MutableLiveData<CurrentsViewState>()
    val state = _state

    fun getCurrents(){
        repository.getAllCurrents()
            .doOnSubscribe{
                _state.postValue(CurrentsViewState.InProgress)
            }
            .doOnError {
                Timber.e(it)
                _state.postValue(CurrentsViewState.Failed)
            }
            .subscribe {
                if(it.isEmpty())
                    _state.postValue(CurrentsViewState.Empty)
                else
                    _state.postValue(CurrentsViewState.Response(it))
            }.addTo(disposeBag)
    }
}
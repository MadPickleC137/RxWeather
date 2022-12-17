package com.madpickle.feature_current_forecast.viewmodel

import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.madpickle.core_data.repositories.CurrentWeatherRepository
import com.madpickle.feature_current_forecast.model.CurrentsViewState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by David Madilyan on 10.09.2022.
 */
class CurrentsViewModel @Inject constructor(private val repository: CurrentWeatherRepository): ViewModel() {
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
            }
    }
}
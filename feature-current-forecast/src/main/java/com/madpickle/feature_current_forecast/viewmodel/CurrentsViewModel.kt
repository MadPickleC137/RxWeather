package com.madpickle.feature_current_forecast.viewmodel

import androidx.lifecycle.MutableLiveData
import com.madpickle.core_android.viewmodel.BaseViewModel
import com.madpickle.core_data.model.CurrentModel
import com.madpickle.core_data.repositories.ICurrentWeatherRepository
import com.madpickle.feature_current_forecast.state.CurrentsViewState
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by David Madilyan on 10.09.2022.
 */
class CurrentsViewModel @Inject constructor(private val currentRepo: ICurrentWeatherRepository): BaseViewModel() {
    private val _state = MutableLiveData<CurrentsViewState>()
    val state = _state

    fun updateCurrent(currentModel: CurrentModel, index: Int){
        currentRepo.getCurrentApi(currentModel.region)
            .doOnError {
                Timber.i(it)
            }
            .subscribe {
                if(it.region.isNotEmpty()){
                    currentRepo.insertCurrentModel(it).subscribe {
                        _state.postValue(CurrentsViewState.Updated(it, index))
                    }
                }
            }.addTo(disposeBag)
    }

    fun deleteCurrent(currentModel: CurrentModel, index: Int){
        currentRepo.deleteCurrentModel(currentModel.region).doOnError {
            Timber.i(it)
        }.subscribe {
            _state.postValue(CurrentsViewState.Deleted(currentModel, index))
        }
    }

    fun getCurrents(){
        currentRepo.getAllCurrents()
            .doOnSubscribe {
                _state.postValue(CurrentsViewState.InProgress)
            }
            .doOnError {
                Timber.i(it)
                _state.postValue(CurrentsViewState.Failed)
            }
            .subscribe {
                if(it.isEmpty()) _state.postValue(CurrentsViewState.Empty)
                else _state.postValue(CurrentsViewState.Response(it))
            }.addTo(disposeBag)
    }
}
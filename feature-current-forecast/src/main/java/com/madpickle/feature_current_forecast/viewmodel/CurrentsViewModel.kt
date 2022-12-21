package com.madpickle.feature_current_forecast.viewmodel

import androidx.lifecycle.MutableLiveData
import com.madpickle.core_android.viewmodel.BaseViewModel
import com.madpickle.core_data.model.CurrentModel
import com.madpickle.core_data.repositories.ICurrentWeatherRepository
import com.madpickle.core_data.repositories.ILocationRepository
import com.madpickle.feature_current_forecast.model.CurrentsViewState
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by David Madilyan on 10.09.2022.
 */
class CurrentsViewModel @Inject constructor(private val currentRepo: ICurrentWeatherRepository,
                                            private val locationRepo: ILocationRepository
                                            ): BaseViewModel() {
    private val _state = MutableLiveData<CurrentsViewState>()
    val state = _state

    private fun initNewCurrent() {
        locationRepo.getLocation()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnError { Timber.i(it) }
            .subscribe {
                if(it.name.isNotBlank()){
                    getCurrentByRegion(it.name)
                }
            }.addTo(disposeBag)
    }

    fun updateCurrent(currentModel: CurrentModel){
        getCurrentByRegion(currentModel.region)
    }

    private fun getCurrentByRegion(nameReg: String) {
        currentRepo.syncCurrent(nameReg)
            .doOnSubscribe {}
            .doOnError {
                Timber.i(it)
            }
            .subscribe {
                if(it.region.isNotEmpty()){
                    locationRepo.deleteLocation().subscribe {}
                    currentRepo.insertCurrentModel(it).subscribe {
                        _state.postValue(CurrentsViewState.Response(listOf(it)))
                    }
                }
            }.addTo(disposeBag)
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
                initNewCurrent()
                if(it.isEmpty()) _state.postValue(CurrentsViewState.Empty)
                else _state.postValue(CurrentsViewState.Response(it))
            }.addTo(disposeBag)
    }
}
package com.madpickle.feature_days_forecast.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.madpickle.core_android.viewmodel.BaseViewModel
import com.madpickle.core_data.model.DayModel
import com.madpickle.core_data.model.HourModel
import com.madpickle.core_data.repositories.IForecastRepository
import com.madpickle.feature_days_forecast.state.ForecastViewState
import javax.inject.Inject

/**
 * Created by David Madilyan on 18.12.2022.
 */
class ForecastViewModel @Inject constructor(private val forecastRepo: IForecastRepository): BaseViewModel() {
    private var region: String = ""
    private val _state = MutableLiveData<ForecastViewState>()
    val state: LiveData<ForecastViewState> = _state
    fun setArgs(_region: String){
        region = _region
    }

    fun onHourItemSelected(hourModel: HourModel) {
        _state.value = ForecastViewState.OnHourChange(hourModel)
    }

    fun onDayItemChanged(dayModel: DayModel){
        _state.value = ForecastViewState.OnDayChange(dayModel)
    }

    fun getForecast(){
        forecastRepo.getForecast(region)
            .doOnSubscribe {
                _state.value = ForecastViewState.Loading
            }
            .doOnError {
                _state.value = ForecastViewState.Error
            }
            .subscribe {
                _state.value = ForecastViewState.Response(it)
            }
    }
}
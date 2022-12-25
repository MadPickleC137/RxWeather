package com.madpickle.feature_days_forecast.viewmodel

import com.madpickle.core_android.viewmodel.BaseViewModel
import com.madpickle.core_data.model.HourModel
import com.madpickle.core_data.repositories.IForecastRepository
import javax.inject.Inject

/**
 * Created by David Madilyan on 18.12.2022.
 */
class ForecastViewModel @Inject constructor(private val forecastRepo: IForecastRepository): BaseViewModel() {
    private var region: String = ""

    fun setArgs(_region: String){
        region = _region
    }

    fun onHourItemSelected(hourModel: HourModel) {

    }

}
package com.madpickle.feature_days_forecast.state

import com.madpickle.core_data.model.DayModel
import com.madpickle.core_data.model.ForecastModel
import com.madpickle.core_data.model.HourModel


/**
 * Created by David Madilyan on 25.12.2022.
 */
sealed class ForecastViewState{
    object Loading: ForecastViewState()
    object Error: ForecastViewState()
    data class Response(val forecast: ForecastModel): ForecastViewState()
    data class OnHourChange(val hourModel: HourModel): ForecastViewState()
    data class OnDayChange(val dayModel: DayModel): ForecastViewState()
}

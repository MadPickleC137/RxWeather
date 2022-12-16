package com.madpickle.feature_current_forecast.model

import com.madpickle.core_data.model.CurrentModel

/**
 * Created by David Madilyan on 15.12.2022.
 */
sealed class CurrentsViewState{
    object InProgress: CurrentsViewState()
    object Failed: CurrentsViewState()
    object Empty: CurrentsViewState()
    class Response(val currents: List<CurrentModel>): CurrentsViewState()
}

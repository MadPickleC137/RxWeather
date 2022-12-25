package com.madpickle.feature_current_forecast.state

import com.madpickle.core_data.model.CurrentModel

/**
 * Created by David Madilyan on 15.12.2022.
 */
sealed class CurrentsViewState{
    object InProgress: CurrentsViewState()
    object Failed: CurrentsViewState()
    object Empty: CurrentsViewState()
    class Response(val currents: List<CurrentModel>): CurrentsViewState()
    class Updated(val current: CurrentModel, val index: Int): CurrentsViewState()
    class Deleted(val current: CurrentModel, val index: Int): CurrentsViewState()
}

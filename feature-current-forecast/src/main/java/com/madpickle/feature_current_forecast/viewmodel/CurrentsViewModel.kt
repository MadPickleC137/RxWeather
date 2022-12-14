package com.madpickle.feature_current_forecast.viewmodel

import androidx.lifecycle.ViewModel
import com.madpickle.core_data.repositories.CurrentWeatherRepository
import javax.inject.Inject

/**
 * Created by David Madilyan on 10.09.2022.
 */
class CurrentsViewModel @Inject constructor(private val repository: CurrentWeatherRepository): ViewModel() {

}
package com.madpickle.core_network

import com.madpickle.core_network.model.AstronomyResponse
import com.madpickle.core_network.model.CurrentResponse
import com.madpickle.core_network.model.ForecastResponse
import com.madpickle.core_network.model.Place
import io.reactivex.rxjava3.core.Observer

/**
 * Created by David Madilyan on 23.08.2022.
 */
interface IWeatherNetworkSource {

    fun subscribeCurrent(location: String, observer: Observer<CurrentResponse>)

    fun subscribeForecast(location: String, days: Int, observer: Observer<ForecastResponse>)

    fun subscribeAstronomy(location: String, date: String, observer: Observer<AstronomyResponse>)

    fun subscribeSearchPlaces(place: String, observer: Observer<List<Place>>)
}
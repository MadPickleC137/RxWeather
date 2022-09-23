package com.madpickle.core_network

import com.madpickle.core_network.model.AstronomyResponse
import com.madpickle.core_network.model.CurrentResponse
import com.madpickle.core_network.model.ForecastResponse
import com.madpickle.core_network.model.Place
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class WeatherNetworkSource @Inject constructor (private val apiService: IWeatherService):
    IWeatherNetworkSource {

    override fun subscribeCurrent(location: String, observer: Observer<CurrentResponse>) {
        apiService.getCurrentWeather(location).subscribeOn(Schedulers.io()).subscribe(observer)
    }

    override fun subscribeForecast(
        location: String,
        days: Int,
        observer: Observer<ForecastResponse>
    ) {
        apiService.getForecastByDays(location, days).subscribeOn(Schedulers.io()).subscribe(observer)
    }

    override fun subscribeAstronomy(
        location: String,
        date: String,
        observer: Observer<AstronomyResponse>
    ) {
        apiService.getAstronomyByDate(location, date).subscribeOn(Schedulers.io()).subscribe(observer)
    }

    override fun subscribeSearchPlaces(place: String, observer: Observer<List<Place>>) {
        apiService.searchByPlace(place).subscribeOn(Schedulers.io()).subscribe(observer)
    }

}
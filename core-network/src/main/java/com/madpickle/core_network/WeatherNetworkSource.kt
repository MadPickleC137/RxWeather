package com.madpickle.core_network

import com.madpickle.core_network.model.*
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class WeatherNetworkSource @Inject constructor (private val apiService: IWeatherService):
    IWeatherNetworkSource {

    override fun getCurrentWeather(location: String): Observable<CurrentResponse> {
       return apiService.getCurrentWeather(location).subscribeOn(Schedulers.io())
    }

    override fun getForecastByDays(location: String, days: Int): Observable<ForecastResponse> {
      return apiService.getForecastByDays(location, days).subscribeOn(Schedulers.io())
    }

    override fun getAstronomy(location: String, date: String, ): Observable<AstronomyResponse> {
        return apiService.getAstronomyByDate(location, date).subscribeOn(Schedulers.io())
    }

    override fun searchPlaces(regionName: String): Observable<List<Place>> {
       return apiService.searchByPlace(regionName).subscribeOn(Schedulers.io())
    }

}
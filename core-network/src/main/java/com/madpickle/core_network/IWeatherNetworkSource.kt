package com.madpickle.core_network

import com.madpickle.core_network.model.AstronomyResponse
import com.madpickle.core_network.model.CurrentResponse
import com.madpickle.core_network.model.ForecastResponse
import com.madpickle.core_network.model.Place
import io.reactivex.rxjava3.core.Observable

/**
 * Created by David Madilyan on 23.08.2022.
 */
interface IWeatherNetworkSource {

    fun getCurrentWeather(location: String): Observable<CurrentResponse>

    fun getForecastByDays(location: String, days: Int): Observable<ForecastResponse>

    fun getAstronomy(location: String, date: String): Observable<AstronomyResponse>

    fun searchPlaces(regionName: String): Observable<List<Place>>
}
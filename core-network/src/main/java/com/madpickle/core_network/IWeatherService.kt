package com.madpickle.core_network

import com.madpickle.core_network.model.AstronomyResponse
import com.madpickle.core_network.model.CurrentResponse
import com.madpickle.core_network.model.ForecastResponse
import com.madpickle.core_network.model.Place
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by David Madilyan on 18.07.2022.
 */
interface IWeatherService {
    @GET("current.json")
    fun getCurrentWeather(@Query("q") location: String): Observable<CurrentResponse>

    @GET("forecast.json")
    fun getForecastByDays(
        @Query("q") location: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "yes",
    ): Observable<ForecastResponse>

    @GET("astronomy.json")
    fun getAstronomyByDate(
        @Query("q") location: String,
        @Query("dt") date: String,
    ): Observable<AstronomyResponse>

    @GET("search.json")
    fun searchByPlace(@Query("q") place: String): Observable<List<Place>>
}
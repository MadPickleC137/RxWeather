package com.madpickle.core_data.repositories

import com.madpickle.core_android.repository.BaseRepository
import com.madpickle.core_data.model.*
import com.madpickle.core_data.realm_db.*
import com.madpickle.core_network.IWeatherNetworkSource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by David Madilyan on 04.12.2022.
 */
interface IForecastRepository{
    fun deleteForecast(region: String): Completable
    fun getForecast(region: String, daysCount: Int): Observable<ForecastModel>
}

internal class ForecastRepository @Inject constructor(
    private val forecastDao: ForecastDao,
    private val networkSource: IWeatherNetworkSource
): BaseRepository(), IForecastRepository {

    /**
     * Получает актуальные данные из кэша и сети, данные полученные из сети записываются в кэш
     * */
    override fun getForecast(region: String, daysCount: Int): Observable<ForecastModel>{
        return Observable.concatArrayEager(
            forecastDao.getForecastByRegion(region).toObservable(),
            networkSource.getForecastByDays(region, daysCount)
            .observeOn(AndroidSchedulers.mainThread())
            .map { response ->
                val model = ForecastModel.InitForecastWithDays(response)
                forecastDao.insertOrUpdate(model)
                model
            }.debounce(400, TimeUnit.MILLISECONDS)
        )
    }

    /**
     * Удаляет прогноз погоды
     *
     * @param region регион по которому удаляется из кэша прогноз
     * */
    override fun deleteForecast(region: String): Completable{
        return forecastDao.deleteByRegion(region)
    }
}
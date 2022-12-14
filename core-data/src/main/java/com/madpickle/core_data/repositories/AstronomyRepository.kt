package com.madpickle.core_data.repositories

import com.madpickle.core_data.model.AstronomyModel
import com.madpickle.core_data.realm_db.AstronomyDao
import com.madpickle.core_network.IWeatherNetworkSource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by David Madilyan on 10.12.2022.
 *
 * Получает информацию о восходе, закате и что то о луне
 */

interface IAstronomyRepository {
    fun getAstronomy( region: String, date: String): Observable<AstronomyModel>
    fun deleteByRegion(region: String): Completable
}

class AstronomyRepository @Inject constructor(private val astronomyDao: AstronomyDao,
                                              private val networkSource: IWeatherNetworkSource
): IAstronomyRepository {

    /**
     * Получает актуальные данные из кэша и сети, данные полученные из сети записываются в кэш
     * */
    override fun getAstronomy( region: String, date: String): Observable<AstronomyModel> {
        return Observable.concatArrayEager(
            astronomyDao.getAstronomy(date, region).toObservable(),
            networkSource.getAstronomy(region, date).observeOn(AndroidSchedulers.mainThread()).map {
                val model = AstronomyModel.InitAstronomyModel(it.astronomy?.astro, date, region)
                astronomyDao.insertOrUpdate(model)
                model
            }.debounce(400, TimeUnit.MILLISECONDS)
        )
    }

    override fun deleteByRegion(region: String): Completable {
        return astronomyDao.deleteByDate(region)
    }
}

package com.madpickle.core_data.repositories

import com.madpickle.core_android.repository.BaseRepository
import com.madpickle.core_data.model.CurrentModel
import com.madpickle.core_data.realm_db.CurrentWeatherDao
import com.madpickle.core_network.IWeatherNetworkSource
import com.madpickle.core_network.model.CurrentResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by David Madilyan on 28.08.2022.
 *
 * Работает с [CurrentModel]. Модель приходит с кэша и с сети в виде типа [CurrentResponse] и конвертируется в необходимый тип
 *
 * Принцип работы:
 *                                  - DB
 *                                /
 * ViewModel <-----> Repository /
 *                              \
 *                               \
 *                                 - API
 */
interface ICurrentWeatherRepository {
    fun getCurrentApi(region: String): Observable<CurrentModel>
    fun insertCurrentModel(currentModel: CurrentModel): Completable
    fun deleteCurrentModel(region: String): Completable
    fun getAllCurrents(): Observable<List<CurrentModel>>
}

internal class CurrentWeatherRepository @Inject constructor(
    private val dao: CurrentWeatherDao,
    private val networkSource: IWeatherNetworkSource
): BaseRepository(), ICurrentWeatherRepository {

    /**
     * Получает модель из двух разных источников данных и синхронизирует Observable
     * @param region сторока по которой получаем данные
     * */
    override fun getCurrentApi(region: String): Observable<CurrentModel> {
        return networkSource.getCurrentWeather(region)
            .map {
                it.current?.let {
                    current -> CurrentModel.InitCurrent(current, region)
                } ?: CurrentModel()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun insertCurrentModel(currentModel: CurrentModel): Completable {
       return dao.insertOrUpdate(currentModel)
    }

    override fun deleteCurrentModel(region: String): Completable {
        return dao.deleteByRegion(region)
    }

    override fun getAllCurrents(): Observable<List<CurrentModel>> {
        return dao.getAllCurrents().toObservable()
    }
}
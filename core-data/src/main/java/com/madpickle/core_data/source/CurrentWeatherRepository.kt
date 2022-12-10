package com.madpickle.core_data.source

import com.madpickle.core_data.model.CurrentModel
import com.madpickle.core_data.RepositoryListener
import com.madpickle.core_data.realm_db.CurrentWeatherDao
import com.madpickle.core_network.IWeatherNetworkSource
import com.madpickle.core_network.model.CurrentResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Notification
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit
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
    fun getCurrentObservable(region: String, error: RepositoryListener): Observable<CurrentModel>
    fun insertCurrentModel(currentModel: CurrentModel): Completable
    fun deleteCurrentModel(region: String): Completable
}

class CurrentWeatherRepository @Inject constructor(
    private val dao: CurrentWeatherDao,
    private val networkSource: IWeatherNetworkSource
): ICurrentWeatherRepository {

    /**
     * Получает модель из двух разных источников данных и синхронизирует Observable
     * @param region сторока по которой получаем данные
     * @param error Слушатель на случай прихода с микросервиса ошибки
     * */
    override fun getCurrentObservable(region: String, error: RepositoryListener): Observable<CurrentModel> {
        return Observable.concatArrayEager(
            dao.getCurrentByRegion(region).toObservable(),
            networkSource.getCurrentWeather(region)
                .materialize()
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    it.error?.let { error.onError() }
                    it
                }
                .filter { !it.isOnError && it.value?.current != null }
                .dematerialize {
                    val model = CurrentModel.InitCurrent(it.value?.current!!, region)
                    dao.insertOrUpdate(model)
                    Notification.createOnNext(model)
                }
                .debounce(400, TimeUnit.MILLISECONDS)
        )
    }

    override fun insertCurrentModel(currentModel: CurrentModel): Completable {
       return dao.insertOrUpdate(currentModel)
    }

    override fun deleteCurrentModel(region: String): Completable {
        return dao.deleteByRegion(region)
    }
}
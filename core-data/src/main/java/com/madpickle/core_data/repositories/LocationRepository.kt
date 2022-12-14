package com.madpickle.core_data.repositories

import com.madpickle.core_android.repository.BaseRepository
import com.madpickle.core_data.model.LocationModel
import com.madpickle.core_data.realm_db.LocationDao
import com.madpickle.core_network.IWeatherNetworkSource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by David Madilyan on 31.08.2022.
 */

interface ILocationRepository{
    fun searchLocations(regionName: String): Observable<List<LocationModel>>
    fun getLocation(): Observable<LocationModel>
    fun setLocation(locationModel: LocationModel): Completable
    fun deleteLocation(): Completable
}

internal class LocationRepository@Inject constructor(
    private val dao: LocationDao,
    private val networkSource: IWeatherNetworkSource
): BaseRepository(), ILocationRepository {

    /**
     * Реализация  запроса в сеть для быстрого поиска места, необходимо для выборки прогнозов
     * */
    override fun searchLocations(regionName: String): Observable<List<LocationModel>> {
        return networkSource.searchPlaces(regionName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { list ->
                list.map { LocationModel.InitLocation(it) }
            }
    }

    /**
     * Получает из кэша модель локации, которую выбрал пользователь
     * */
    override fun getLocation(): Observable<LocationModel> {
        return dao.getLocation().toObservable()
    }

    /**
     * Устанавливает выбранную пользователем локацию
     * */
    override fun setLocation(locationModel: LocationModel): Completable {
        return dao.insertOrUpdate(locationModel)
    }

    /**
     * Удаляет их кэша выбранную пользователь локацию
     * */
    override fun deleteLocation(): Completable {
        return dao.deleteAll()
    }
}

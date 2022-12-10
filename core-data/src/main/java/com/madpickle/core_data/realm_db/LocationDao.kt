package com.madpickle.core_data.realm_db

import com.madpickle.core_data.executeCompletable
import com.madpickle.core_data.executeSingleAsync
import com.madpickle.core_data.getSingleInstance
import com.madpickle.core_data.model.LocationModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.realm.Realm
import io.realm.kotlin.where

/**
 * Created by David Madilyan on 31.08.2022.
 *
 * Кэшируем всегда только одно место пользователя выбранное либо по геолокации, либо самим пользователем
 *
 * [LocationModel]
 */
class LocationDao(private val realm: Realm) {

    fun getLocation(): Single<LocationModel>{
        return getSingleInstance().flatMap {
            realm.executeSingleAsync {
                realm.where<LocationModel>().findFirst()
            }
        }
    }

    fun insertOrUpdate(model: LocationModel): Completable {
        return realm.executeCompletable {
            realm.insertOrUpdate(model)
        }
    }

    fun deleteAll(): Completable {
        return realm.executeCompletable {
            realm.where<LocationModel>().findAllAsync().deleteAllFromRealm()
        }
    }
}
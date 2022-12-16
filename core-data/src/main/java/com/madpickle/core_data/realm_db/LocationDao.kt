package com.madpickle.core_data.realm_db

import com.madpickle.core_data.executeCompletable
import com.madpickle.core_data.executeSingle
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
class LocationDao {

    fun getLocation(): Single<LocationModel>{
        return Realm.getDefaultInstance().use{
            it.executeSingle {
                it.where<LocationModel>().findFirstAsync()
            }
        }
    }

    fun insertOrUpdate(model: LocationModel): Completable {
        return Realm.getDefaultInstance().use{ realm ->
            realm.executeCompletable {
                realm.insertOrUpdate(model)
            }
        }
    }

    fun deleteAll(): Completable {
        return Realm.getDefaultInstance().use{ realm ->
            realm.executeCompletable {
                realm.where<LocationModel>()
                    .findAll()
                    .deleteAllFromRealm()
            }
        }
    }
}
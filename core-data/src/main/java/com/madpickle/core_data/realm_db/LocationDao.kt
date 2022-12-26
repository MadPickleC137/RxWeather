package com.madpickle.core_data.realm_db

import com.madpickle.core_data.executeCompletable
import com.madpickle.core_data.executeRealm
import com.madpickle.core_data.model.LocationModel
import com.madpickle.core_data.model.LocationWrapper
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.realm.kotlin.where

/**
 * Created by David Madilyan on 31.08.2022.
 *
 * Кэшируем всегда только одно место пользователя выбранное либо по геолокации, либо самим пользователем
 *
 * [LocationModel]
 */
class LocationDao {
    fun getLocation(): Maybe<LocationModel> {
        return executeRealm { realm ->
            realm.where(LocationModel::class.java)
                .isNotNull("region")
                .findFirst()?.copy() ?: LocationModel()
        }
    }

    fun insertOrUpdate(model: LocationModel): Completable {
        return executeCompletable {
            it.insertOrUpdate(LocationWrapper(model))
        }
    }

    fun deleteAll(): Completable {
        return executeCompletable {
            it.where<LocationWrapper>()
                .findAll()
                .deleteAllFromRealm()
        }
    }
}
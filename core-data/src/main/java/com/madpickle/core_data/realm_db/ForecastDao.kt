package com.madpickle.core_data.realm_db

import com.madpickle.core_data.executeCompletable
import com.madpickle.core_data.executeSingleAsync
import com.madpickle.core_data.getSingleInstance
import com.madpickle.core_data.model.ForecastModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.realm.Realm
import io.realm.kotlin.where

/**
 * Created by David Madilyan on 02.09.2022.
 */
class ForecastDao(private val realm: Realm) {

    fun getForecastByRegion(region: String): Single<ForecastModel> {
        return getSingleInstance().flatMap {
            realm.executeSingleAsync {
                realm.where<ForecastModel>().equalTo("region", region).findFirst()
            }
        }
    }

    fun insertOrUpdate(model: ForecastModel?): Completable {
        return realm.executeCompletable {
            if (model != null) {
                realm.insertOrUpdate(model)
            }
        }
    }

    fun deleteByRegion(region: String): Completable {
        return realm.executeCompletable {
            realm.where<ForecastModel>().findFirst()?.deleteFromRealm()
        }
    }
}
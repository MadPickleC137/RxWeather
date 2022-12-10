package com.madpickle.core_data.realm_db

import com.madpickle.core_data.executeCompletable
import com.madpickle.core_data.executeSingleAsync
import com.madpickle.core_data.getSingleInstance
import com.madpickle.core_data.model.CurrentModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.realm.Realm
import io.realm.kotlin.where

/**
 * Created by David Madilyan on 27.08.2022.
 *
 * В таблице всегда хранятся данные только одного экземпляра [CurrentModel].
 * Если мы обновляем прогноз погоды, то данные [CurrentModel] тоже должны обновляться и в таблице.
 */

class CurrentWeatherDao (private val realm: Realm) {

    fun getCurrentByRegion(region: String): Single<CurrentModel>{
        return getSingleInstance().flatMap {
            realm.executeSingleAsync {
                realm.where<CurrentModel>().equalTo("region", region).findFirst()
            }
        }
    }

    fun insertOrUpdate(model: CurrentModel): Completable {
        return realm.executeCompletable {
            realm.insertOrUpdate(model)
        }
    }

    fun deleteByRegion(region: String): Completable {
        return realm.executeCompletable {
            realm.where<CurrentModel>().equalTo("region", region).findFirst()?.deleteFromRealm()
        }
    }
}
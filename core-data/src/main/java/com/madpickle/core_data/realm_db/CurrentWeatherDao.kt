package com.madpickle.core_data.realm_db

import com.madpickle.core_data.executeCompletable
import com.madpickle.core_data.executeSingle
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

class CurrentWeatherDao {
    fun getCurrentByRegion(region: String): Single<CurrentModel>{
        return Realm.getDefaultInstance().use {
            it.executeSingle {
                it.where<CurrentModel>().equalTo("region", region).findFirstAsync()
            }
        }
    }

    fun getAllCurrents(): Single<List<CurrentModel>> {
        return Realm.getDefaultInstance().use {
            it.executeSingle {
                it.where<CurrentModel>().findAll()
            }
        }
    }

    fun insertOrUpdate(model: CurrentModel): Completable {
        return Realm.getDefaultInstance().use { realm ->
            realm.executeCompletable { realm.insertOrUpdate(model) }
        }
    }

    fun deleteByRegion(region: String): Completable {
        return Realm.getDefaultInstance().use { realm ->
            realm.executeCompletable {
                realm.where<CurrentModel>().equalTo("region", region)
                    .findFirst()
                    ?.deleteFromRealm()
            }
        }
    }
}
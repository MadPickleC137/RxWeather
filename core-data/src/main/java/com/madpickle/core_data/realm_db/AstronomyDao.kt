package com.madpickle.core_data.realm_db

import com.madpickle.core_data.executeCompletable
import com.madpickle.core_data.executeSingleAsync
import com.madpickle.core_data.getSingleInstance
import com.madpickle.core_data.model.AstronomyModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.realm.Realm
import io.realm.kotlin.where

/**
 * Created by David Madilyan on 02.09.2022.
 */
class AstronomyDao(private val realm: Realm) {

    fun getAstronomy(date: String, region: String): Single<AstronomyModel> {
        return getSingleInstance().flatMap {
            realm.executeSingleAsync {
                realm.where<AstronomyModel>().equalTo("date", date).and().equalTo("region", region).findFirst()
            }
        }
    }

    fun insertOrUpdate(model: AstronomyModel?): Completable {
        return realm.executeCompletable {
            if (model != null) {
                realm.insertOrUpdate(model)
            }
        }
    }

    fun deleteByDate(region: String): Completable{
        return realm.executeCompletable {
            realm.where<AstronomyModel>().equalTo("region", region).findAll().deleteAllFromRealm()
        }
    }
}
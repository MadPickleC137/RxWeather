package com.madpickle.core_data.realm_db

import com.madpickle.core_data.executeCompletable
import com.madpickle.core_data.executeSingle
import com.madpickle.core_data.model.AstronomyModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.realm.Realm
import io.realm.kotlin.where

/**
 * Created by David Madilyan on 02.09.2022.
 */
class AstronomyDao() {

    fun getAstronomy(date: String, region: String): Single<AstronomyModel> {
        return Realm.getDefaultInstance().use { realm ->
            realm.executeSingle {
                realm.where<AstronomyModel>()
                    .equalTo("date", date)
                    .and()
                    .equalTo("region", region)
                    .findFirstAsync()
            }
        }
    }

    fun insertOrUpdate(model: AstronomyModel?): Completable {
        return Realm.getDefaultInstance().use { realm ->
            realm.executeCompletable {
                if (model != null) {
                    realm.insertOrUpdate(model)
                }
            }
        }
    }

    fun deleteByDate(region: String): Completable{
        return Realm.getDefaultInstance().use { realm ->
            realm.executeCompletable {
                realm.where<AstronomyModel>().equalTo("region", region)
                    .findAll()
                    .deleteAllFromRealm()
            }
        }
    }
}
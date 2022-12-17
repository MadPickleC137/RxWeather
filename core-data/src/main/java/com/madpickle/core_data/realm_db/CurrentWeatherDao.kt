package com.madpickle.core_data.realm_db

import com.madpickle.core_data.executeCompletable
import com.madpickle.core_data.executeSingle
import com.madpickle.core_data.model.CurrentModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.realm.kotlin.where

/**
 * Created by David Madilyan on 27.08.2022.
 *
 * В таблице всегда хранятся данные только одного экземпляра [CurrentModel].
 * Если мы обновляем прогноз погоды, то данные [CurrentModel] тоже должны обновляться и в таблице.
 */

class CurrentWeatherDao {
    fun getCurrentByRegion(region: String): Single<CurrentModel>{
        return executeSingle {
            it.where<CurrentModel>().equalTo("region", region).findFirst()
        }
    }

    fun getAllCurrents(): Single<List<CurrentModel>> {
        return executeSingle {
            it.where<CurrentModel>().findAll().toList()
        }
    }

    fun insertOrUpdate(model: CurrentModel): Completable {
        return  executeCompletable { it.insertOrUpdate(model) }
    }

    fun deleteByRegion(region: String): Completable {
        return executeCompletable {
            it.where<CurrentModel>().equalTo("region", region)
                .findFirst()
                ?.deleteFromRealm()
        }
    }
}
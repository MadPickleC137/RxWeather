package com.madpickle.core_data.realm_db

import com.madpickle.core_data.executeCompletable
import com.madpickle.core_data.executeRealm
import com.madpickle.core_data.model.CurrentModel
import com.madpickle.core_data.model.CurrentWrapper
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.realm.kotlin.where

/**
 * Created by David Madilyan on 27.08.2022.
 *
 * В таблице всегда хранятся данные только одного экземпляра [CurrentModel].
 * Если мы обновляем прогноз погоды, то данные [CurrentModel] тоже должны обновляться и в таблице.
 */

class CurrentWeatherDao {
    fun getCurrentByRegion(region: String): Maybe<CurrentModel>{
        return executeRealm {
            it.where<CurrentModel>().equalTo("region", region)
                .findFirst()?.copy() ?: CurrentModel()
        }
    }

    fun getAllCurrents(): Maybe<List<CurrentModel>> {
        return executeRealm { realm ->
            realm.where(CurrentModel::class.java).findAll().map { it?.copy() ?: CurrentModel() }
        }
    }

    fun insertOrUpdate(model: CurrentModel): Completable {
        return executeCompletable {
            it.where<CurrentModel>().equalTo("region", model.region)
                ?.findAll()
                ?.deleteAllFromRealm()
            it.insertOrUpdate(CurrentWrapper(model))
        }
    }

    fun deleteByRegion(region: String): Completable {
        return executeCompletable {
            it.where<CurrentWrapper>().equalTo("currentModel.region", region)
                .findFirst()
                ?.deleteFromRealm()
        }
    }
}
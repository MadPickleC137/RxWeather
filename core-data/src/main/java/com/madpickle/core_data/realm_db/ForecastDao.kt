package com.madpickle.core_data.realm_db

import com.madpickle.core_data.executeCompletable
import com.madpickle.core_data.executeSingle
import com.madpickle.core_data.model.ForecastModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.realm.kotlin.where

/**
 * Created by David Madilyan on 02.09.2022.
 */
class ForecastDao {
    fun getForecastByRegion(region: String): Single<ForecastModel> {
        return executeSingle {
            it.where<ForecastModel>().equalTo("region", region).findFirst()
        }
    }

    fun insertOrUpdate(model: ForecastModel?): Completable {
        return executeCompletable {
            if (model != null) {
                it.insertOrUpdate(model)
            }
        }
    }

    fun deleteByRegion(region: String): Completable {
        return executeCompletable {
            it.where<ForecastModel>()
                .equalTo("region", region)
                .findFirst()
                ?.deleteFromRealm()
        }
    }
}
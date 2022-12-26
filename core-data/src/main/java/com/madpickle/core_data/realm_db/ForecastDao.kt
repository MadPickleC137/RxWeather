package com.madpickle.core_data.realm_db

import com.madpickle.core_data.executeCompletable
import com.madpickle.core_data.executeRealm
import com.madpickle.core_data.model.ForecastModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.realm.kotlin.where

/**
 * Created by David Madilyan on 02.09.2022.
 */
class ForecastDao {
    fun getForecastByRegion(region: String): Maybe<ForecastModel> {
        return executeRealm {
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
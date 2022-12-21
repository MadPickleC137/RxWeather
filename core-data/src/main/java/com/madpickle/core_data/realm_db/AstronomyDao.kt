package com.madpickle.core_data.realm_db

import com.madpickle.core_data.executeCompletable
import com.madpickle.core_data.executeSingle
import com.madpickle.core_data.model.AstronomyModel
import com.madpickle.core_data.model.AstronomyWrapper
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.realm.kotlin.where

/**
 * Created by David Madilyan on 02.09.2022.
 */
class AstronomyDao {

    fun getAstronomy(date: String, region: String): Single<AstronomyModel> {
        return executeSingle {
            it.where<AstronomyWrapper>()
                .equalTo("astronomyModel.date", date)
                .and()
                .equalTo("astronomyModel.region", region)
                .findFirst()?.astronomyModel ?: AstronomyModel()
        }
    }

    fun insertOrUpdate(model: AstronomyModel?): Completable {
        return executeCompletable {
            if (model != null) {
                it.insertOrUpdate(AstronomyWrapper(model))
            }
        }
    }

    fun deleteByDate(region: String): Completable{
        return executeCompletable {
            it.where<AstronomyWrapper>().equalTo("astronomyModel.region", region)
                .findAll()
                .deleteAllFromRealm()
        }
    }
}
package com.madpickle.core_data.room_db.dao

import androidx.room.*
import com.madpickle.core_data.model.CurrentModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

/**
 * Created by David Madilyan on 27.08.2022.
 *
 * В таблице всегда хранятся данные только одного экземпляра [CurrentModel].
 * Если мы обновляем прогноз погоды, то данные [CurrentModel] тоже должны обновляться и в таблице.
 */
@Dao
interface CurrentWeatherDao {
    @Query("SELECT * FROM current_weather WHERE region_current =:region")
    fun getCurrentByRegion(region: String): Flowable<CurrentModel>

    @Transaction
    fun insertOrUpdate(model: CurrentModel) {
        model.region?.let {
            deleteByRegion(it)
            insert(model)
        } ?: insert(model)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(model: CurrentModel): Completable

    @Query("DELETE FROM current_weather WHERE region_current =:region")
    fun deleteByRegion(region: String): Completable
}
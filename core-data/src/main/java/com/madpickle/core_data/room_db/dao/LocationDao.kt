package com.madpickle.core_data.room_db.dao

import androidx.room.*
import com.madpickle.core_data.model.LocationModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * Created by David Madilyan on 31.08.2022.
 * Кэшируем всегда только одно место пользователя выбранное либо по геолокации, либо самим пользователем
 *
 * [LocationModel]
 */
@Dao
interface LocationDao {
    @Query("SELECT * FROM location_weather")
    fun getLocation(): Single<LocationModel>

    @Transaction
    fun insertOrUpdate(model: LocationModel) {
        deleteAll()
        insert(model)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(model: LocationModel): Completable


    @Query("DELETE FROM location_weather")
    fun deleteAll(): Completable
}
package com.madpickle.core_data.room_db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.madpickle.core_data.model.AlertModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * Created by David Madilyan on 31.08.2022.
 */
@Dao
interface AlertDao {

    @Query("SELECT * FROM alerts WHERE region_alert = :region")
    fun selectByRegion(region: String): Single<AlertModel>

    /**
     * После отображения алерта в пушах, удалить его из кэша
     */
    @Query("DELETE FROM alerts WHERE region_alert = :region")
    fun deleteByRegion(region: String): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlerts(models: List<AlertModel>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlert(model: AlertModel): Completable

    @Query("DELETE FROM alerts")
    fun deleteAll(): Completable
}
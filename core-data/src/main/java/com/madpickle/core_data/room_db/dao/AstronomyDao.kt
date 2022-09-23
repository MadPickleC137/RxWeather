package com.madpickle.core_data.room_db.dao

import androidx.room.*
import com.madpickle.core_data.model.AstronomyModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

/**
 * Created by David Madilyan on 02.09.2022.
 */
@Dao
interface AstronomyDao {
    @Query("SELECT * FROM astronomy WHERE date =:date")
    fun getAstronomyByDate(date: String): Flowable<AstronomyModel>

    @Transaction
    fun insertOrUpdate(model: AstronomyModel) {
        model.date?.let {
            deleteByDate(model.date)
        }
        insert(model)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(model: AstronomyModel): Completable


    @Query("DELETE FROM astronomy WHERE date =:date")
    fun deleteByDate(date: String): Completable
}
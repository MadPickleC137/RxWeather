package com.madpickle.core_data.room_db.dao

import androidx.room.*
import com.madpickle.core_data.model.ForecastWithDays
import io.reactivex.rxjava3.core.Flowable

/**
 * Created by David Madilyan on 02.09.2022.
 */
@Dao
interface ForecastWithDaysDao {

    @Query("SELECT * FROM location_weather, current_weather WHERE region=:region AND region_current =:region")
    fun getForecastByRegion(region: String): Flowable<ForecastWithDays>

    @Transaction
    fun deleteForecastWithDays(region: String){
        deleteLocationByRegion(region)
        deleteCurrentByRegion(region)
        deleteDayForecastByRegion(region)
        deleteAlertsByRegion(region)
    }

    @Query("DELETE FROM location_weather WHERE region=:region")
    fun deleteLocationByRegion(region: String)

    @Query("DELETE FROM current_weather WHERE region_current=:region")
    fun deleteCurrentByRegion(region: String)

    @Query("DELETE FROM day_forecast WHERE region_day=:region")
    fun deleteDayForecastByRegion(region: String)

    @Query("DELETE FROM alerts WHERE region_alert=:region")
    fun deleteAlertsByRegion(region: String)
}
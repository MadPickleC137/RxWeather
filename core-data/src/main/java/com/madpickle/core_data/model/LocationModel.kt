package com.madpickle.core_data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.madpickle.core_network.model.BaseResponse

/**
 * Created by David Madilyan on 28.08.2022.
 */
@Entity(tableName = "location_weather")
data class LocationModel(
    @PrimaryKey(autoGenerate = true) val idLocation: Int = 0,
    val name: String?,
    val region: String?,
    val country: String?,
    val lat: Double?,
    val lon: Double?,
    val tzId: String?,
    val localtimeEpoch: Long?,
    val localtime: String?,
){
    companion object{
        fun InitLocation(response: BaseResponse): LocationModel{
            return LocationModel(
                name = response.location?.name,
                region = response.location?.region,
                country = response.location?.country,
                lat = response.location?.lat,
                lon = response.location?.lon,
                localtimeEpoch = response.location?.localtimeEpoch,
                tzId = response.location?.tzId,
                localtime = response.location?.localtime,
            )
        }
    }
}
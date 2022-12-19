package com.madpickle.core_data.model

import com.madpickle.core_network.model.Location
import com.madpickle.core_network.model.Place
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import io.realm.annotations.Required
import java.util.*

/**
 * Created by David Madilyan on 28.08.2022.
 */
@RealmClass(embedded = true)
open class LocationModel(
    var idLocation: String? = UUID.randomUUID().toString(),
    var name: String? = null,
    @Required
    var region: String = "",
    var country: String? = null,
    var lat: Double? = null,
    var lon: Double? = null,
    var tzId: String? = null,
    var localtimeEpoch: Long? = null,
    var localtime: String? = null,
): RealmObject() {
    companion object{
        fun InitLocation(location: Location): LocationModel{
            return LocationModel(
                name = location.name,
                region = location.region ?: "",
                country = location.country,
                lat = location.lat,
                lon = location.lon,
                localtimeEpoch = location.localtimeEpoch,
                tzId = location.tzId,
                localtime = location.localtime,
            )
        }

        fun InitLocation(place: Place): LocationModel{
            return LocationModel(
                name = place.name,
                region = place.region ?: "",
                country = place.country,
                lat = place.lat,
                lon = place.lon,
                localtimeEpoch = null,
                tzId = null,
                localtime = null,
            )
        }
    }
}
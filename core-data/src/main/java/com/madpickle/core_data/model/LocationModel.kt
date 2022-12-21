package com.madpickle.core_data.model

import com.madpickle.core_network.model.Location
import com.madpickle.core_network.model.Place
import io.realm.RealmModel
import io.realm.annotations.RealmClass

/**
 * Created by David Madilyan on 28.08.2022.
 *
 * Способ создания сущности через [RealmModel]
 */
@RealmClass(embedded = true)
open class LocationModel: RealmModel {
    var name: String = ""
    var region: String = ""
    var country: String? = null
    var lat: Double? = null
    var lon: Double? = null
    var tzId: String? = null
    var localtimeEpoch: Long? = null
    var localtime: String? = null

    constructor(name: String, region: String, country: String?, lat: Double?, lon: Double?, tzId: String?, localtimeEpoch: Long?, localtime: String?){
        this.name = name
        this.region = region
        this.country = country
        this.lat = lat
        this.lon = lon
        this.tzId = tzId
        this.localtimeEpoch = localtimeEpoch
        this.localtime = localtime
    }

    constructor()

    fun copy(): LocationModel{
        return LocationModel(name, region, country, lat, lon, tzId, localtimeEpoch, localtime)
    }

    companion object{
        fun InitLocation(location: Location): LocationModel{
            return LocationModel(
                location.name ?: "",
                location.region ?: "",
                location.country,
                location.lat,
                location.lon,
                location.tzId,
                location.localtimeEpoch,
                location.localtime,
            )
        }

        fun InitLocation(place: Place): LocationModel{
            return LocationModel(
                name = place.name ?: "",
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
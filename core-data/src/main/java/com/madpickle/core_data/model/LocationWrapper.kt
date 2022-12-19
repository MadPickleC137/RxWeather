package com.madpickle.core_data.model

import io.realm.RealmObject

/**
 * Created by David Madilyan on 19.12.2022.
 */

internal open class LocationWrapper(
    var locationModel: LocationModel? = null
): RealmObject()

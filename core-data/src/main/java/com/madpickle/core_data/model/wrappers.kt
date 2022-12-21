package com.madpickle.core_data.model

import io.realm.RealmObject
import io.realm.annotations.Required

/**
 * Created by David Madilyan on 20.12.2022.
 *
 * Классы обертки для моделей, чтобы их можно было использовать в realm как отдельные сущности.
 *
 * Необходимо для поддержки CRUD запросов
 */

internal open class LocationWrapper(
    var locationModel: LocationModel? = null
): RealmObject()

internal open class AlertWrapper(
    var alertModel: AlertModel? = null
): RealmObject()

internal open class AstronomyWrapper(
    var astronomyModel: AstronomyModel? = null
): RealmObject()

internal open class CurrentWrapper(
    var currentModel: CurrentModel? = null
): RealmObject()

internal open class HourWrapper(
    var hourModel: HourModel? = null
): RealmObject()

internal open class DayWrapper(
    var dayModel: DayModel? = null
): RealmObject()
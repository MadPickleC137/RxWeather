package com.madpickle.core_data.model

import com.madpickle.core_network.model.Alert
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import io.realm.annotations.Required
import java.util.*

/**
 * Created by David Madilyan on 28.08.2022.
 */
@RealmClass(embedded = true)
open class AlertModel(
    var id: String = UUID.randomUUID().toString(),
    var headline: String? = null,
    var areas: String? = null,
    @Required
    var region: String = "",                                                                             //регион по которому будут определяться алерты в кэше
    var note: String? = null,
    var effective: String? = null,
    var expires: String? = null,
    var instruction: String? = null,
    var category: String? = null,                                                                          // категория на английском
    var event: String? = null,                                                                             //тип придупреждения
    var desc: String? = null,
) : RealmObject() {
    companion object{
        fun InitAlertModel(alert: Alert, regionCurrent: String?): AlertModel{
            return AlertModel(
                headline = alert.headline,
                areas = alert.areas,
                note = alert.note,
                effective = alert.effective,
                expires = alert.expires,
                instruction = alert.instruction,
                category = alert.category,
                event = alert.event,
                desc = alert.desc,
                region = regionCurrent ?:"",
            )
        }
    }
}

package com.madpickle.core_data.model

import com.madpickle.core_network.model.Alert
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required
import java.util.*

/**
 * Created by David Madilyan on 28.08.2022.
 */
@RealmClass(embedded = true)
open class AlertModel(
    @PrimaryKey
    @Required
    val id: String = UUID.randomUUID().toString(),
    var headline: String?,
    var areas: String?,
    var region: String,                                                                             //регион по которому будут определяться алерты в кэше
    var note: String?,
    var effective: String?,
    var expires: String?,
    var instruction: String?,
    var category: String?,                                                                          // категория на английском
    var event: String?,                                                                             //тип придупреждения
    var desc: String?,
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

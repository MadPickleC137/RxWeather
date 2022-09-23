package com.madpickle.core_data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.madpickle.core_network.model.Alert

/**
 * Created by David Madilyan on 28.08.2022.
 */
@Entity(tableName = "alerts")
data class AlertModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val headline: String?,
    val areas: String?,
    @ColumnInfo(name = "region_alert")
    val region: String,                                                                             //регион по которому будут определяться алерты в кэше
    val note: String?,
    val effective: String?,
    val expires: String?,
    val instruction: String?,
    val category: String?,                                                                          // категория на английском
    val event: String?,                                                                             //тип придупреждения
    val desc: String?,
){
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

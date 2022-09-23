package com.madpickle.core_network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by David Madilyan on 20.08.2022.
 */
data class AirQuality(
    val co: Float?,
    val o3: Float?,
    val no2: Float?,
    val so2: Float?,
    @SerializedName("pm2_5") val pm25: Float?,                                    // (показатель загрязнения) взвешенные твердые микрочастицы и мельчайшие капельки жидкости (10 нм - 2,5 мкм в диаметре)
    val pm10: Float?,                                                             //любые твердые частицы в воздухе диаметром 10 микрометров или меньше, включая дым, пыль, сажу, соли, кислоты и металлы
)

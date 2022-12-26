package com.madpickle.core_data.model

/**
 * Created by David Madilyan on 26.12.2022.
 */
internal enum class UvIndex(val text: String) {
    Nan("Непонятно"),
    Low("Низкий"),
    Medium("Средний"),
    High("Высокий"),
    VeryHigh("Очень высокий"),
    Extremal("Поджаришься"),
}

internal fun getUvIndex(uv: Double?): UvIndex{
    if(uv == null) return UvIndex.Nan
    return when{
        uv in 0.0..2.0 -> UvIndex.Low
        uv > 2.0 && uv < 6.0 -> UvIndex.Medium
        uv >= 6.0 && uv < 8.0 -> UvIndex.High
        uv >= 8.0 && uv < 11.0 -> UvIndex.VeryHigh
        uv >= 11.0 -> UvIndex.Extremal
        else -> UvIndex.Nan
    }
}
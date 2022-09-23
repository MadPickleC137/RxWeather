package com.madpickle.core_network.model

/**
 * Created by David Madilyan on 31.07.2022.
 */
data class Alert(
    val headline: String?,
    val areas: String?,
    val note: String?,
    val effective: String?,
    val expires: String?,
    val instruction: String?,
    val category: String?,                                                                          // категория на английском
    val event: String?,                                                                             //тип придупреждения
    val desc: String?,
)
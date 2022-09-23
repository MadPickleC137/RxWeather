package com.madpickle.core_network.model

/**
 * Created by David Madilyan on 20.08.2022.
 */
data class AstronomyResponse(
    val astronomy: AstronomyItem?,
): BaseResponse()

data class AstronomyItem(val astro: Astro?)

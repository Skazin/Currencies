package com.currencies.android_test_shared.mock

import com.currencies.model.UiRate

object MockUiRate {

    fun getUiRate(
        id: Long? = null,
        currency: String = "",
        rate: Double = 0.0,
        isFavourite: Boolean = true
    ): UiRate = UiRate(
        id = id,
        currency = currency,
        rate = rate,
        isFavourite = isFavourite
    )
}
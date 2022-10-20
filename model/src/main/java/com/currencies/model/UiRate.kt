package com.currencies.model

/**
 * This class describe a currency rate.
 */
data class UiRate(

    /**
     * Currency rate id.
     */
    val id: Long? = null,

    /**
     * Three-letter currency name.
     */
    val currency: String = "",

    /**
     * Value of exchange rate of current currency.
     */
    val rate: Double = 0.0,

    /**
     * Flag if currency is chosen favourite.
     */
    var isFavourite: Boolean = false
) {
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (other is UiRate) return this.currency == other.currency
//        return true
//    }
//
//    override fun hashCode(): Int {
//        return super.hashCode()
//    }
}
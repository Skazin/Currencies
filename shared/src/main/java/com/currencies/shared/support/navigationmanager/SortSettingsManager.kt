package com.currencies.shared.support.navigationmanager

import javax.inject.Inject

interface SortSettingsManager {

    var sortSettings: SortType?

}

class SortSettingsManagerImpl @Inject constructor(): SortSettingsManager {

    override var sortSettings: SortType? = null

}

enum class SortType {
    ALPH_ASC_RATE_ASC,
    ALPH_ASC_RATE_DESC,
    ALPH_DESC_RATE_ASC,
    ALPH_DESC_RATE_DESC
}
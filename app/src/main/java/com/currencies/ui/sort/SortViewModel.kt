package com.currencies.ui.sort

import com.currencies.shared.support.navigationmanager.SortSettingsManager
import com.currencies.shared.support.navigationmanager.SortType
import com.currencies.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SortViewModel @Inject constructor(
    private val sortSettingsManager: SortSettingsManager
) : BaseViewModel(
    sortSettingsManager = sortSettingsManager
) {

    private val _alphabetAscChecked = MutableStateFlow(true)
    val alphabetAscChecked = _alphabetAscChecked.asStateFlow()

    private val _alphabetDescChecked = MutableStateFlow(false)
    val alphabetDescChecked = _alphabetDescChecked.asStateFlow()

    private val _rateAscChecked = MutableStateFlow(true)
    val rateAscChecked = _rateAscChecked.asStateFlow()

    private val _rateDescChecked = MutableStateFlow(false)
    val rateDescChecked = _rateDescChecked.asStateFlow()

    init {
        when (sortSettingsManager.sortSettings) {
            SortType.ALPH_ASC_RATE_ASC -> {
                _alphabetAscChecked.value = true
                _alphabetDescChecked.value = false
                _rateAscChecked.value = true
                _rateDescChecked.value = false
            }
            SortType.ALPH_ASC_RATE_DESC -> {
                _alphabetAscChecked.value = true
                _alphabetDescChecked.value = false
                _rateAscChecked.value = false
                _rateDescChecked.value = true
            }
            SortType.ALPH_DESC_RATE_ASC -> {
                _alphabetAscChecked.value = false
                _alphabetDescChecked.value = true
                _rateAscChecked.value = true
                _rateDescChecked.value = false
            }
            SortType.ALPH_DESC_RATE_DESC -> {
                _alphabetAscChecked.value = false
                _alphabetDescChecked.value = true
                _rateAscChecked.value = false
                _rateDescChecked.value = true
            }
            else -> {}
        }
        checkState()
    }

    fun checkState(
        alphabetAscChecked: Boolean = _alphabetAscChecked.value,
        rateAscChecked: Boolean = _rateAscChecked.value
    ) {
        if (alphabetAscChecked) {
            if (!rateAscChecked) changeSortType(SortType.ALPH_ASC_RATE_DESC)
            else changeSortType(SortType.ALPH_ASC_RATE_ASC)
        } else {
            if (rateAscChecked) changeSortType(SortType.ALPH_DESC_RATE_ASC)
            else changeSortType(SortType.ALPH_DESC_RATE_DESC)
        }
    }

    private fun changeSortType(sortType: SortType) {
        sortSettingsManager.sortSettings = sortType
    }
}
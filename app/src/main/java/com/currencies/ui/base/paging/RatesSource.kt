package com.currencies.ui.base.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.currencies.model.UiRate
import com.currencies.shared.domain.currencies.GetUiRateListUseCase
import com.currencies.shared.result.Result

const val PAGE_SIZE = 5
const val INITIAL_LOAD_SIZE = 5

class RatesSource(
    private val currency: String,
    private val getUiRateListUseCase: GetUiRateListUseCase
) : PagingSource<Int, UiRate>() {

    override fun getRefreshKey(state: PagingState<Int, UiRate>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UiRate> {
        val nextPage = params.key ?: 1
        val ratesList = mutableListOf<UiRate>()

        return when (val result = getUiRateListUseCase.execute(currency)) {
            is Result.Success -> {
                result.data.forEach { uiRate ->
                    ratesList.add(uiRate)
                }
                LoadResult.Page(
                    data = ratesList,
                    prevKey = if (nextPage == 1) null else nextPage - 1,
                    nextKey = if (ratesList.isEmpty()) null else nextPage + 1
                )
            }
            is Result.Error -> LoadResult.Error(result.exception)
        }
    }
}
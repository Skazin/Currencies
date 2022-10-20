package com.currencies.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.paging.ExperimentalPagingApi
import com.currencies.ui.base.NavGraph
import com.currencies.ui.theme.CurrenciesTheme
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @OptIn(
        ExperimentalCoroutinesApi::class,
        ExperimentalFoundationApi::class,
        InternalCoroutinesApi::class,
        ExperimentalMaterialApi::class,
        ExperimentalAnimationApi::class,
        ExperimentalPagingApi::class
    )
    @SuppressLint("VisibleForTests")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                CurrenciesTheme {
                    ProvideWindowInsets{
                        NavGraph()
                    }
                }
            }
        }
    }
}
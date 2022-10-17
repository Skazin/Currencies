package com.currencies.ui.base

import DropDownList
import android.annotation.SuppressLint
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.currencies.R
import com.currencies.ui.theme.mainGray
import com.currencies.ui.theme.white
import com.currencies.ui.util.extensions.noRippleClickable

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun SettingsCard(
    onCurrencySelect: (String) -> Unit
) {
    val text = remember { mutableStateOf("BYN") } // initial value

    val isOpen = remember { mutableStateOf(false) } // initial value
    val openCloseOfDropDownList: (Boolean) -> Unit = {
        isOpen.value = it
    }
    val transitionState = remember {
        MutableTransitionState(isOpen.value).apply {
            targetState = !isOpen.value
        }
    }
    val transition = updateTransition(transitionState, label = "Arrow animation")
    val arrowRotationDegree by transition.animateFloat(
        {
            tween(durationMillis = 100)
        }, label = "Arrow animation"
    ) { if (isOpen.value) 180f else 0f }

    Card(
        backgroundColor = white,
        elevation = 0.dp,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 5.dp, top = 5.dp, end = 32.dp, bottom = 5.dp)
        ) {
            Box(
                modifier = Modifier.wrapContentWidth()
            ) {
                OutlinedTextField(
                    value = text.value,
                    readOnly = true,
                    enabled = false,
                    onValueChange = {},
                    modifier = Modifier
                        .wrapContentWidth()
                        .noRippleClickable {
                            isOpen.value = true
                        }
                )
                DropDownList(
                    requestToOpen = isOpen.value,
                    selectedString = {
                        onCurrencySelect(it)
                        text.value = it
                                     },
                    request = openCloseOfDropDownList
                )

                AnimatedArrow(
                    degrees = arrowRotationDegree,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(0.dp, 8.dp, 8.dp, 0.dp)
                        .noRippleClickable {
                            isOpen.value = true
                        }
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_filter),
                tint = mainGray,
                contentDescription = "filter icon"
            )
        }

    }
}

@Composable
private fun AnimatedArrow(
    degrees: Float,
    modifier: Modifier
) {
    Icon(
        imageVector = Icons.Filled.ArrowDropDown,
        contentDescription = "Animated arrow",
        modifier = modifier.rotate(degrees),
    )
}
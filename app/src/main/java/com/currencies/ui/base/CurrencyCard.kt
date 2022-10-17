package com.currencies.ui.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.currencies.ui.theme.*
import com.currencies.R
import com.currencies.ui.util.extensions.noRippleClickable

@Composable
fun CurrencyCard(
    currencyName: String,
    currencyRate: Double,
    isFavourite: Boolean = false,
    onFavouriteClick: (Boolean) -> Unit
) {

    val clicked = remember { mutableStateOf(isFavourite) }

    Card(
        backgroundColor = white,
        elevation = 0.dp,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = currencyName,
                style = H3,
                color = mainGray,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = currencyRate.toString(),
                style = M2,
                color = textGray,
                modifier = Modifier
                    .padding(start = 5.dp)
                    .weight(2f)
            )
            Image(
                painter = painterResource(
                    id = if (clicked.value) R.drawable.ic_star_filled else R.drawable.ic_star_line
                ),
                modifier = Modifier
                    .size(40.dp)
                    .noRippleClickable {
                        onFavouriteClick(clicked.value)
                        clicked.value = !clicked.value
                    },
                contentDescription = "favourite star"
            )
        }
    }
}
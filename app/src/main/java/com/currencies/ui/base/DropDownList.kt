import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.currencies.ui.theme.white

@Composable
fun DropDownList(
    requestToOpen: Boolean = false,
    request: (Boolean) -> Unit,
    selectedString: (String) -> Unit
) {
    val currencyList =  listOf(
        "BYN",
        "EUR",
        "USD",
        "PLN",
        "RUB"
    )

    DropdownMenu(
        modifier = Modifier.wrapContentWidth().background(white),
        expanded = requestToOpen,
        onDismissRequest = { request(false) },
    ) {
        currencyList.forEach {
            DropdownMenuItem(
                modifier = Modifier.wrapContentWidth(),
                onClick = {
                    request(false)
                    selectedString(it)
                }
            ) {
                Text(it, modifier = Modifier.wrapContentWidth())
            }
        }
    }
}
package com.scalio.ui.home

import android.graphics.Typeface
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.scalio.R
import com.scalio.ui.main.nav.Nav
import com.scalio.ui.main.showToast
import com.scalio.ui.theme.ScalioTheme
import com.scalio.ui.theme.appBG
import com.scalio.ui.theme.blue300

@Composable
fun HomeView(
    nav: Nav,
    homeViewModel: HomeViewModel = hiltViewModel(),
) = ScalioTheme {

    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
        Column {
            val context = LocalContext.current
            val user = searchInputText()

            val textId = when (val uiState = homeViewModel(rememberCoroutineScope())) {
                HomeViewState.Idle -> R.string.txt_submit
                is HomeViewState.InputFail -> {
                    homeViewModel.reset()
                    context.showToast(uiState.error)
                    R.string.txt_submit
                }
                is HomeViewState.Success -> {
                    homeViewModel.reset()
                    nav.openSearchView(user)
                    R.string.txt_success
                }
            }

            SubmitButton(textId) {
                homeViewModel.searchUsers(user)
            }
        }
    }
}

@Composable
private fun searchInputText(): String {
    var query by remember { mutableStateOf("") }
    Row {
        TextField(
            value = query,
            onValueChange = {
                query = it
            },
            modifier = Modifier
                .padding(top = 48.dp, bottom = 16.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = blue300,
                fontFamily = FontFamily(Typeface.DEFAULT_BOLD),
            ),
            label = { Text(text = stringResource(R.string.txt_search)) }
        )
    }

    return query
}

@Composable
private fun SubmitButton(
    @StringRes textId: Int,
    onClick: () -> Unit
) = Row {
    Button(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .padding(top = 16.dp, bottom = 16.dp, start = 32.dp, end = 32.dp)
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        onClick = onClick,
        content =
        {
            Text(
                text = stringResource(textId),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = appBG
            )
        }
    )
}

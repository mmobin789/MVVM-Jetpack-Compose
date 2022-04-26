package com.scalio.ui.search

import android.graphics.Typeface
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scalio.R
import com.scalio.ui.main.nav.Nav
import com.scalio.ui.main.showToast
import com.scalio.ui.theme.RentNPayTheme
import com.scalio.ui.theme.appBG
import com.scalio.ui.theme.appTextBG

@Composable
fun SearchView(
    nav: Nav,
    searchViewIntent: SearchViewIntent
) = RentNPayTheme {

    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
        Column {
            val context = LocalContext.current
            when (val uiState = searchViewIntent(rememberCoroutineScope()).value) {
                SearchViewState.Idle -> {
                    searchViewIntent.searchUsers(nav.parseSearchViewArgs().query)
                }
                SearchViewState.Loading -> {
                    context.showToast(R.string.txt_loading)
                }
                is SearchViewState.Success -> {
                    context.showToast(uiState.users.totalCount.toString())
                }
                is SearchViewState.Fail -> {}
            }

            LocationInput()
            SearchInput()
            BrowseCategoryAndSeeAllLabels()
            CategoryList()
        }
    }
}

@Composable
private fun LocationInput() =
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(color = appTextBG, shape = RoundedCornerShape(8.dp))
    ) {
        Image(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
            painter = painterResource(R.drawable.ic_outline_location_on_24),
            contentDescription = null
        )
        Text(
            modifier = Modifier.wrapContentSize(),
            text = "Punjab,Pakistan",
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontFamily = FontFamily(Typeface.DEFAULT_BOLD)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_round_keyboard_arrow_down_24),
                contentDescription = null
            )
        }
    }

@Composable
private fun SearchInput() = Row(verticalAlignment = Alignment.CenterVertically) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
            .weight(.8f)
            .background(color = appTextBG, shape = RoundedCornerShape(8.dp))
    ) {
        Image(
            modifier = Modifier.padding(8.dp),
            painter = painterResource(R.drawable.ic_outline_search_24),
            contentDescription = null
        )
        Text(
            modifier = Modifier.wrapContentSize(),
            text = stringResource(id = R.string.category_search_input),
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontFamily = FontFamily(Typeface.DEFAULT_BOLD)
        )
    }
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .weight(.1f)
            .background(appBG)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_outline_notifications_24),
            contentDescription = null
        )
    }
}

@Composable
private fun BrowseCategoryAndSeeAllLabels() =
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .weight(.5f)
        ) {
            Text(
                modifier = Modifier.wrapContentSize(),
                text = stringResource(id = R.string.category_browse_category_label),
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .weight(.5f)
                .background(appBG)
        ) {
            Text(
                modifier = Modifier.wrapContentSize(),
                text = stringResource(id = R.string.category_see_all_label),
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                fontFamily = FontFamily(Typeface.DEFAULT_BOLD)
            )
        }
    }

@Composable
private fun CategoryList() = LazyRow(content = {
    // todo fetch categories from firebase as UI ready.
    for (i in 0..9)
        item(null) {
            Column(verticalArrangement = Arrangement.Center) {
                Image(
                    painter = painterResource(R.drawable.ic_outline_category_view_24),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(8.dp)
                        .background(shape = CircleShape, color = appTextBG)
                        .padding(16.dp)
                )
                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 8.dp),
                    text = stringResource(id = R.string.category_label),
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Typeface.DEFAULT_BOLD)
                )
            }
        }
})

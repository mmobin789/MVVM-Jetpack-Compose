package com.scalio.ui.search

import android.graphics.Typeface
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
import com.scalio.ui.search.model.remote.User
import com.scalio.ui.theme.RentNPayTheme
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
            val args = nav.parseSearchViewArgs()
            val users = args.users.toMutableList()
            val listState = rememberLazyListState()
            when (val uiState = searchViewIntent(rememberCoroutineScope()).value) {
                SearchViewState.Idle -> {
                    if (listState.isScrollInProgress && listState.firstVisibleItemIndex == users.lastIndex) {
                        searchViewIntent.searchUsers(args.query)
                    }
                }
                SearchViewState.Loading -> {
                    context.showToast(R.string.txt_loading)
                }
                is SearchViewState.Success -> {
                    users.addAll(uiState.data.users)
                    searchViewIntent.reset()
                }
                is SearchViewState.Fail -> {
                    context.showToast(uiState.error)
                    searchViewIntent.reset()
                }
            }

            UsersList(users, listState)
        }
    }
}

@Composable
private fun UsersList(users: List<User>, listState: LazyListState) =
    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxWidth(),
        content = {
            users.forEach {
                item(null) {
                    Column(
                        horizontalAlignment = CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
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
                                .padding(horizontal = 8.dp)
                                .padding(vertical = 8.dp),
                            text = "${stringResource(id = R.string.user_label)} ${it.login}",
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp,
                            fontFamily = FontFamily(Typeface.DEFAULT_BOLD)
                        )
                        Divider(thickness = 1.dp)
                    }
                }
            }
        }
    )

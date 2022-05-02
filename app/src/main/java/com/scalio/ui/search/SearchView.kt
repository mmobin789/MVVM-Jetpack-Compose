package com.scalio.ui.search

import android.graphics.Typeface
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.scalio.R
import com.scalio.ui.main.nav.Nav
import com.scalio.ui.main.showToast
import com.scalio.ui.search.model.remote.GithubUser
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
            val user = nav.parseSearchViewArgs()

            UsersList(
                searchViewIntent.searchUsers(user, rememberCoroutineScope())
                    .collectAsLazyPagingItems()
            ) {
                it.run {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            context.showToast(R.string.txt_loading)
                            // You can add modifier to manage load state when first time response page is loading
                        }
                        loadState.append is LoadState.Loading -> {
                            // You can add modifier to manage load state when next response page is loading
                        }
                        loadState.append is LoadState.Error -> {
                            val errorState = loadState.append as LoadState.Error
                            context.showToast(errorState.error.message)
                            // You can use modifier to show error message
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun UsersList(
    githubUsers: LazyPagingItems<GithubUser>,
    onList: (LazyPagingItems<GithubUser>) -> Unit
) {
    LazyColumn {
        items(githubUsers) { item ->
            item?.also {
                GitHubUserView(githubUser = it)
            }
        }
    }

    onList(githubUsers)
}

@Composable
fun GitHubUserView(githubUser: GithubUser) = Column(
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
        text = "${stringResource(id = R.string.user_label)} ${githubUser.login}",
        textAlign = TextAlign.Center,
        fontSize = 15.sp,
        fontFamily = FontFamily(Typeface.DEFAULT_BOLD)
    )
    Divider(thickness = 1.dp)
}

package com.scalio.ui.search

import android.graphics.Typeface
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.scalio.R
import com.scalio.ui.main.nav.Nav
import com.scalio.ui.main.showToast
import com.scalio.ui.search.model.remote.GithubUser
import com.scalio.ui.theme.ScalioTheme
import com.scalio.ui.theme.appTextBG
import com.skydoves.landscapist.coil.CoilImage
import kotlinx.coroutines.flow.Flow

@Composable
fun SearchView(
    nav: Nav,
    searchViewIntent: SearchViewIntent
) = ScalioTheme {

    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
        Column {
            val user = nav.parseSearchViewArgs()
            when (val state = searchViewIntent(rememberCoroutineScope())) {
                SearchViewState.Loading -> {
                    searchViewIntent.searchUsers(user)
                }
                is SearchViewState.UserListing -> {
                    UsersList(state.flowPagingData)
                }
            }
        }
    }
}

@Composable
private fun UsersList(
    githubUsersFlow: Flow<PagingData<GithubUser>>
) {
    val githubUsers = githubUsersFlow.collectAsLazyPagingItems()
    val context = LocalContext.current

    LazyColumn {

        items(githubUsers) { item ->
            GitHubUserView(githubUser = item!!)
        }
        githubUsers.run {

            when {

                loadState.refresh is LoadState.Loading -> {
                    item {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = CenterHorizontally,
                            modifier = Modifier.fillParentMaxSize()
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    val errorState = loadState.refresh as LoadState.Error

                    if (errorState.endOfPaginationReached) {
                        context.showToast(R.string.txt_success)
                    } else {
                        context.showToast(errorState.error.message)
                    }
                }
                loadState.append is LoadState.Loading -> {
                    // You can add modifier to manage load state when next response page is loading
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .wrapContentWidth(CenterHorizontally)
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    // You can use modifier to show error message
                    val errorState = loadState.append as LoadState.Error

                    if (errorState.endOfPaginationReached) {
                        context.showToast(R.string.txt_success)
                    } else {
                        item {
                            RetryButton(errorState.error.message) {
                                retry()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RetryButton(error: String?, onClick: () -> Unit) = Row(
    modifier = Modifier.padding(16.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    Text(
        text = error.orEmpty(),
        maxLines = 1,
        modifier = Modifier.weight(1f),
        style = MaterialTheme.typography.h6,
        color = Red
    )
    OutlinedButton(onClick = onClick) {
        Text(text = stringResource(id = R.string.retry_label))
    }
}

@Composable
private fun GitHubUserView(githubUser: GithubUser) = Column(
    horizontalAlignment = CenterHorizontally,
    verticalArrangement = Arrangement.Center
) {
    CoilImage(
        imageRequest = ImageRequest.Builder(LocalContext.current)
            .data(githubUser.avatarUrl)
            .size(120)
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_image_24)
            .crossfade(true)
            .transformations(CircleCropTransformation())
            .build(),
        modifier = Modifier
            .size(120.dp)
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

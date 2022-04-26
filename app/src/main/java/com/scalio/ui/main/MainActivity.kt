package com.scalio.ui.main

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.scalio.ui.home.HomeView
import com.scalio.ui.home.HomeViewIntent
import com.scalio.ui.main.nav.Nav
import com.scalio.ui.search.SearchView
import com.scalio.ui.search.SearchViewIntent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var homeViewIntent: HomeViewIntent

    @Inject
    lateinit var searchViewIntent: SearchViewIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavView(homeViewIntent, searchViewIntent)
        }
    }
}

fun Context.showToast(s: String?) = Toast.makeText(this, s.orEmpty(), Toast.LENGTH_SHORT).show()

fun Context.showToast(@StringRes id: Int) = Toast.makeText(this, id, Toast.LENGTH_SHORT).show()

@Composable
private fun NavView(homeViewIntent: HomeViewIntent, searchViewIntent: SearchViewIntent) {
    val nav = Nav(rememberNavController())

    NavHost(navController = nav.navHostController, startDestination = Nav.HOME) {

        composable(Nav.HOME) {
            HomeView(nav = nav, homeViewIntent = homeViewIntent)
        }

        composable(
            Nav.SEARCH
        ) {
            SearchView(nav, searchViewIntent)
        }
    }
}

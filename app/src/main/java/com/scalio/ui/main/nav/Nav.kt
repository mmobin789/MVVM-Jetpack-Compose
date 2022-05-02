package com.scalio.ui.main.nav

import android.os.Bundle
import androidx.navigation.NavHostController

class Nav(val navHostController: NavHostController) {

    private val args = Bundle()

    //  fun openHomeView() = navHostController.navigate(HOME)

    fun openSearchView(user: String) {
        args.putString("user", user)
        navHostController.navigate(SEARCH)
    }

    fun parseSearchViewArgs() = args.getString("user")!!

    companion object Route {
        const val HOME = "home"
        const val SEARCH = "search"
    }
}

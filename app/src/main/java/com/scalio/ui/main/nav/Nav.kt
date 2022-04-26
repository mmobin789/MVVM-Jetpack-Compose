package com.scalio.ui.main.nav

import android.os.Bundle
import androidx.navigation.NavHostController
import com.scalio.ui.search.model.remote.Users

class Nav(val navHostController: NavHostController) {

    private val args = Bundle()

    //  fun openHomeView() = navHostController.navigate(HOME)

    fun openSearchView(users: Users) {
        args.putParcelable("users", users)
        navHostController.navigate(SEARCH)
    }

    fun parseSearchViewArgs() = args.getParcelable<Users>("users")!!

    companion object Route {
        const val HOME = "home"
        const val SEARCH = "search"
    }
}

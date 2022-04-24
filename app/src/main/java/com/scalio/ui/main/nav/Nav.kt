package com.scalio.ui.main.nav

import androidx.navigation.NavHostController

class Nav(val navHostController: NavHostController) {

    //  fun openHomeView() = navHostController.navigate(HOME)

    fun openSearchView(query: String) = navHostController.navigate("search?query=$query")

    fun parseSearchViewArgs(): String {
        return navHostController.getBackStackEntry(SEARCH).arguments?.getString("query").orEmpty()
    }

    companion object Route {
        const val HOME = "home"
        const val SEARCH = "search?query={query}"
    }
}

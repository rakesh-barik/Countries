package com.tinybinlabs.countries.presentation

sealed class Screen(val route: String) {
    object ListScreen : Screen("list_screen")
    object DetailScreen : Screen("detail_screen")
    object FavListScreen : Screen("fav_list_screen")
}
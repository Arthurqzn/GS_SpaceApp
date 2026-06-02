package com.fiap.spaceapp.ui.navigation

sealed class Screen(val route: String) {
    object Home        : Screen("home")
    object Events      : Screen("events")
    object Satellites  : Screen("satellites")
    object Regions     : Screen("regions")
    object EventDetail : Screen("event/{id}") {
        fun go(id: Int) = "event/$id"
    }
}

package com.fiap.spaceapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.fiap.spaceapp.ui.screens.*
import com.fiap.spaceapp.viewmodel.SpaceViewModel

@Composable
fun NavGraph(nav: NavHostController, vm: SpaceViewModel) {
    NavHost(navController = nav, startDestination = Screen.Home.route) {

        composable(Screen.Home.route) {
            HomeScreen(nav)
        }
        composable(Screen.Events.route) {
            EventsScreen(nav, vm)
        }
        composable(Screen.Satellites.route) {
            SatellitesScreen(nav, vm)
        }
        composable(Screen.Regions.route) {
            RegionsScreen(nav, vm)
        }
        composable(
            route     = Screen.EventDetail.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { back ->
            val id = back.arguments?.getInt("id") ?: 0
            EventDetailScreen(nav, id, vm)
        }
    }
}

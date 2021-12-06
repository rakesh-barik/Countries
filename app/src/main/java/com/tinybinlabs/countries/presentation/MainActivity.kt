package com.tinybinlabs.countries.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tinybinlabs.countries.presentation.country.CountryDetailScreen
import com.tinybinlabs.countries.presentation.countrylist.CountryListScreen
import com.tinybinlabs.countries.presentation.favorites.FavoritesListScreen
import com.tinybinlabs.countries.presentation.util.InternetConManager
import com.tinybinlabs.countries.ui.theme.CountriesTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var connectionManager: InternetConManager

    override fun onStart() {
        super.onStart()
        connectionManager.registerConnectionObserver(this)
    }

    override fun onStop() {
        super.onStop()
        connectionManager.unregisterConnectionObserver(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CountriesTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ListScreen.route
                    ) {
                        composable(route = Screen.ListScreen.route) {
                            CountryListScreen(
                                isNetworkAvailable = connectionManager.isNetAvailable.value,
                                onNavigateToDetailScreen = navController::navigate
                            )
                        }
                        composable(
                            route = Screen.DetailScreen.route +
                                    "?countryId={countryId}",
                            arguments = listOf(
                                navArgument(
                                    name = "countryId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }

                            )
                        ) {
                            CountryDetailScreen(navController = navController)
                        }
                        composable(route = Screen.FavListScreen.route) {
                            FavoritesListScreen(
                                navController = navController,
                            )
                        }
                    }
                }
            }
        }

    }
}

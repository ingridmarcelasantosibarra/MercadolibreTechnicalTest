package com.ingrid.mercadolibre.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ingrid.mercadolibre.presentation.screens.main.MainScreen
import com.ingrid.mercadolibre.presentation.screens.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Splash
    ) {
        composable<Splash> {
            SplashScreen(navigateNextScreen = {
              navController.navigate(Main)
            })
        }

        composable<Main> {
            MainScreen(navigateNextScreen = {
                //  navController.navigate()
            })
        }
    }
}
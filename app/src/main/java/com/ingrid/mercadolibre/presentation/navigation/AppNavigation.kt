package com.ingrid.mercadolibre.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ingrid.mercadolibre.presentation.screens.detail.DetailScreen
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
            MainScreen(navigateNextScreen = { id, price, title ->
                navController.navigate(
                    Detail(id = id, price = price, title= title)
                )
            })
        }

        composable<Detail> { backStackEntry ->
            val param = backStackEntry.toRoute<Detail>()
            DetailScreen(
                id = param.id,
                title = param.title,
                price = param.price
            )
        }
    }
}
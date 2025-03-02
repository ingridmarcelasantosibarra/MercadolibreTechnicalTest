package com.ingrid.mercadolibre.presentation.screens.splash

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ingrid.mercadolibre.R
import com.ingrid.mercadolibre.ui.theme.yellow

@Composable
fun SplashScreen(
    navigateNextScreen: () -> Unit,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    var swapPositions by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        splashViewModel.startAnimation()
        splashViewModel.startValidation()
        Handler(Looper.getMainLooper()).postDelayed({
            swapPositions = !swapPositions
        }, 600)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(yellow)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.mercado_libre),
                contentDescription = "",
                modifier = Modifier.size(width = 174.dp, height = 50.dp)
            )
        }
    }

    LaunchedEffect(splashViewModel.isSplashComplete) {
        if (splashViewModel.isSplashComplete) {
            navigateNextScreen()
        }
    }
}
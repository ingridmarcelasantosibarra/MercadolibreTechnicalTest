package com.ingrid.mercadolibre

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ingrid.mercadolibre.presentation.navigation.AppNavigation
import com.ingrid.mercadolibre.ui.theme.MercadolibreTechnicalTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        setContent {
            MercadolibreTechnicalTestTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    AppNavigation()
                }
            }
        }
    }
}
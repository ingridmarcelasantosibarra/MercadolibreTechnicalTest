package com.ingrid.mercadolibre.presentation.screens.splash

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    var isAnimating by mutableStateOf(false)
        private set
    var isSplashComplete by mutableStateOf(false)
        private set

    fun startAnimation() {
        isAnimating = true
    }

    fun startValidation() {
        viewModelScope.launch {
            kotlinx.coroutines.delay(5000L) // 5 segundos
            isSplashComplete = true
        }
    }


}
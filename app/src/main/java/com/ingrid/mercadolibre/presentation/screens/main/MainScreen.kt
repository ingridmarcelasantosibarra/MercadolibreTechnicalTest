package com.ingrid.mercadolibre.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ingrid.mercadolibre.presentation.core.LoaderComponent
import com.ingrid.mercadolibre.presentation.screens.search.SearchBarComponent
import com.ingrid.mercadolibre.ui.theme.gray10

@Composable
fun MainScreen(
    navigateNextScreen: () -> Unit,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val isLoading = mainViewModel.isLoading
    val categories = mainViewModel.listCategories
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            SearchBarComponent(
                searchText = searchQuery,
                tint = gray10,
               placeholder = "Buscar en mercado libre",
                onSearchTextChanged = {
                    searchQuery = it
                    println("OJOOOOO -> $searchQuery")
                },
                onSearchClick = {

                },
                onClearClick = {
                    searchQuery = ""
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            SectionCategories(itemsList = categories, onClickItem = {

            })
        }
    }
    LoaderComponent(modifier = Modifier, showProgress = isLoading, context = context)
}
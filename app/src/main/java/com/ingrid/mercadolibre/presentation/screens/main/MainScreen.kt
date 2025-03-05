package com.ingrid.mercadolibre.presentation.screens.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ingrid.mercadolibre.data.model.search.Result
import com.ingrid.mercadolibre.presentation.core.LoaderComponent
import com.ingrid.mercadolibre.presentation.screens.search.SearchBarComponent
import com.ingrid.mercadolibre.ui.theme.GrayML

@Composable
fun MainScreen(
    navigateNextScreen: (String, Int, String) -> Unit,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val isLoading = mainViewModel.isLoading
    val categories = mainViewModel.listCategories
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    var showText by rememberSaveable { mutableStateOf(false) }
    val resultList: LazyPagingItems<Result> = mainViewModel.searchFlow.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchBarComponent(
                searchText = searchQuery,
                tint = GrayML,
                placeholder = "Buscar en mercado libre",
                onSearchTextChanged = {
                    searchQuery = it
                },
                onSearchClick = {
                    if (it.isNotEmpty()) {
                        mainViewModel.searchProducts(it)
                        showText = true
                    }
                },
                onClearClick = {
                    searchQuery = ""
                    showText = false
                    mainViewModel.clearSearch()
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!showText) {
                SectionCategories(itemsList = categories, onClickItem = {
                    showText = true
                    searchQuery = it.name
                    mainViewModel.searchProducts(it.name)
                })
            }
            AnimatedVisibility(visible = showText) {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(resultList.itemCount) { index ->
                        val product = resultList[index]
                        product?.let {
                            ListContent(
                                context = context,
                                products = it,
                                onClick = { product ->
                                    navigateNextScreen(product.id, product.price, product.title)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
    LoaderComponent(modifier = Modifier, showProgress = isLoading, context = context)
}
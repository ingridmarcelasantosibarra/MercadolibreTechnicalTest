package com.ingrid.mercadolibre.presentation.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ingrid.mercadolibre.presentation.screens.detail.components.AvailableStock
import com.ingrid.mercadolibre.presentation.core.LoaderComponent
import com.ingrid.mercadolibre.presentation.screens.detail.components.Carrousel
import com.ingrid.mercadolibre.presentation.screens.detail.components.Description
import com.ingrid.mercadolibre.presentation.screens.detail.components.Header
import com.ingrid.mercadolibre.presentation.screens.detail.components.PricesShipping
import com.ingrid.mercadolibre.presentation.screens.detail.components.SellerInfo
import com.ingrid.mercadolibre.presentation.screens.detail.components.Shipping
import com.ingrid.mercadolibre.presentation.screens.search.SearchBarComponent
import com.ingrid.mercadolibre.ui.theme.GrayML

@Composable
fun DetailScreen(
    id: String,
    price: Int,
    title: String,
    detailViewModel: DetailViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val detail = detailViewModel.detail
    val description = detailViewModel.description
    val pictures = detailViewModel.pictures
    val itemCondition = detailViewModel.itemCondition
    val pages = detail.pictures.size
    var quantity by rememberSaveable { mutableStateOf("") }
    var cartNumber by rememberSaveable { mutableStateOf("") }
    val isLoading = detailViewModel.isLoading
    LaunchedEffect(key1 = true) {
        detailViewModel.getDetails(id)
        detailViewModel.getDescription(id)
    }
    Scaffold(
        topBar = {
            SearchBarComponent(
                searchText = "",
                tint = GrayML,
                placeholder = "",
                showSearch = false
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .padding(15.dp),
        ) {
            Header(
                itemCondition = itemCondition,
                detail = detail,
                title = title
            )
            Carrousel(
                pages = pages,
                pictures = pictures
            )
            PricesShipping(price = price)
            Shipping()
            AvailableStock(
                context = context,
                quantity = quantity,
                cart = { cart -> cartNumber = cart },
                availableQuantity = detail.initial_quantity,
            )
            SellerInfo(
                location = detail.seller_address,
                detail = detail
            )
            Description(description = description)
        }
    }
    LoaderComponent(modifier = Modifier, showProgress = isLoading, context = context)
}
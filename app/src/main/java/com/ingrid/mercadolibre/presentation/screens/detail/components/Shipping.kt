package com.ingrid.mercadolibre.presentation.screens.detail.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Store
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ingrid.mercadolibre.R


@Composable
fun Shipping() {
    CustomShipAndShop(
        text1 = stringResource(R.string.text_arrive),
        text2 = stringResource(R.string.text_day),
        text3 = stringResource(R.string.text_price_shipping),
        text4 = stringResource(R.string.other_shipping),
        icon = Icons.Outlined.LocalShipping
    )
    CustomShipAndShop(
        text1 = stringResource(R.string.text_pickup),
        text2 = stringResource(R.string.text_from_day),
        text3 = stringResource(R.string.meli_agency),
        text4 = stringResource(R.string.text_show_in_map),
        icon = Icons.Outlined.Store
    )
}

package com.ingrid.mercadolibre.presentation.screens.detail.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ingrid.mercadolibre.R
import com.ingrid.mercadolibre.presentation.core.TextStyleBoldComponent
import com.ingrid.mercadolibre.ui.theme.BlackML
import com.ingrid.mercadolibre.ui.theme.BlueML
import com.ingrid.mercadolibre.util.Util.toFormatCurrencyCOP

@Composable
fun PricesShipping(price: Int) {
    TextStyleBoldComponent(
        textStyleSimple = price.toFormatCurrencyCOP(),
        paddingVertical = 24.dp,
        fontSize = 14.sp,
        modifier = Modifier,
        textAlign = TextAlign.Start,
        tint = BlackML
    )
    TextStyleBoldComponent(
        textStyleSimple =   stringResource(R.string.text_fees),
        paddingHorizontal = 24.dp,
        fontSize = 12.sp,
        modifier = Modifier,
        textAlign = TextAlign.Start,
        tint = BlackML
    )
    TextStyleBoldComponent(
        textStyleSimple =  stringResource(R.string.text_more_info),
        fontSize = 12.sp,
        paddingHorizontal = 24.dp,
        modifier = Modifier,
        textAlign = TextAlign.Start,
        tint = BlueML
    )
}
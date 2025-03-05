package com.ingrid.mercadolibre.presentation.screens.detail.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ingrid.mercadolibre.R
import com.ingrid.mercadolibre.data.model.description.DescriptionResponse
import com.ingrid.mercadolibre.presentation.core.TextStyleBoldComponent
import com.ingrid.mercadolibre.ui.theme.BlackML

@Composable
fun Description(description: DescriptionResponse) {
    TextStyleBoldComponent(
        textStyleBold =   stringResource(R.string.text_description),
        paddingVertical = 24.dp,
        fontSize = 16.sp,
        modifier = Modifier,
        textAlign = TextAlign.Start,
        tint = BlackML
    )
    TextStyleBoldComponent(
        textStyleSimple =  description.plain_text,
        paddingHorizontal = 0.dp,
        fontSize = 14.sp,
        modifier = Modifier,
        textAlign = TextAlign.Start,
        tint = BlackML
    )
}
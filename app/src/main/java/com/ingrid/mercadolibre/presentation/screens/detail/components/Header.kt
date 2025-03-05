package com.ingrid.mercadolibre.presentation.screens.detail.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ingrid.mercadolibre.R
import com.ingrid.mercadolibre.data.model.detail.DetailResponse

@Composable
fun Header(
    itemCondition: String,
    detail: DetailResponse,
    title: String
) {
    Spacer(modifier = Modifier.padding(top = 6.dp))
    Text(
        text = stringResource(id = R.string.text_condition_quantity_sold, itemCondition,detail.sold_quantity),
        style = MaterialTheme.typography.bodySmall.copy(
            fontWeight = FontWeight.Light,
            color = Color.Black
        )
    )
    Text(
        modifier = Modifier.padding(bottom = 18.dp, top = 6.dp),
        text = title,
        style = MaterialTheme.typography.bodyMedium.copy(
            lineHeight = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )
    )
}
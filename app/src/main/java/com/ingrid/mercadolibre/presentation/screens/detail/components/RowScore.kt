package com.ingrid.mercadolibre.presentation.screens.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ingrid.mercadolibre.data.model.detail.DetailResponse
import com.ingrid.mercadolibre.presentation.IconMap.listColorMap
import com.ingrid.mercadolibre.util.Util.getQuantity


@Composable
fun RowScore(
    detail: DetailResponse,
) {
    Spacer(modifier = Modifier.height(30.dp))
    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth()
    ) {
        val boxes = 5
        val space = (maxWidth - 32.dp) / boxes
        val quantity = getQuantity(detail)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(times = boxes) { index ->
                val stroke = if (index == quantity) 10.dp else 6.dp
                Box(
                    modifier = Modifier
                        .background(listColorMap[index])
                        .height(stroke)
                        .width(space)
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

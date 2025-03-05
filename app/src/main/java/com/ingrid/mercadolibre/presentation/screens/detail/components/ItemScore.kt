package com.ingrid.mercadolibre.presentation.screens.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


@Composable
fun ItemScore(
    isIcon: Boolean,
    icon: ImageVector,
    description: String,
    title: String = "",
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isIcon) {
            Icon(imageVector = icon, contentDescription = "", tint = Color.Black)
        } else {
            Text(text = title, color = Color.Black, fontSize = 20.sp)
        }
        Text(
            text = description,
            style = MaterialTheme.typography.labelSmall.copy(
                color = Color.Black,
                fontSize = 10.sp,
                lineHeight = 10.sp,
                textAlign = TextAlign.Center
            )
        )
    }
}

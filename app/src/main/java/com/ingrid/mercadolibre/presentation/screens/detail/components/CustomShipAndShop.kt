package com.ingrid.mercadolibre.presentation.screens.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ingrid.mercadolibre.R
import com.ingrid.mercadolibre.presentation.core.TextStyleBoldComponent
import com.ingrid.mercadolibre.ui.theme.BlackML
import com.ingrid.mercadolibre.ui.theme.BlueML
import com.ingrid.mercadolibre.ui.theme.GreenML

@Composable
fun CustomShipAndShop(
    text1: String,
    text2: String,
    text3: String,
    text4: String,
    icon: ImageVector,
) {
    Spacer(modifier = Modifier.height(10.dp))
    Row(verticalAlignment = Alignment.Top) {
        Icon(imageVector = icon, contentDescription = "", tint = GreenML)
        Column(Modifier.padding(start = 8.dp)) {
            Text(
                text = buildAnnotatedString {
                    append(text1)
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(text2)
                    }
                    withStyle(style = SpanStyle(fontSize = 14.sp)) {
                        append(text3)
                    }
                },
                style = MaterialTheme.typography.bodySmall.copy(
                    color = GreenML,
                    lineHeight = 14.sp
                )

            )
            TextStyleBoldComponent(
                textStyleSimple =  stringResource(R.string.profit_meli_points),
                fontSize = 12.sp,
                modifier = Modifier,
                textAlign = TextAlign.Start,
                tint = BlackML
            )
            TextStyleBoldComponent(
                textStyleSimple =  stringResource(R.string.text_shop_soon),
                textStyleBold =  stringResource(R.string.text_time_reversed),
                isFirstSimpleText = true,
                fontSize = 12.sp,
                modifier = Modifier,
                textAlign = TextAlign.Start,
                tint = BlackML
            )
            TextStyleBoldComponent(
                textStyleSimple =  stringResource(R.string.profit_meli_points),
                fontSize = 12.sp,
                modifier = Modifier,
                textAlign = TextAlign.Start,
                tint = BlackML
            )
            TextStyleBoldComponent(
                textStyleSimple = text4,
                fontSize = 12.sp,
                modifier = Modifier,
                textAlign = TextAlign.Start,
                tint = BlueML
            )
        }
    }
}

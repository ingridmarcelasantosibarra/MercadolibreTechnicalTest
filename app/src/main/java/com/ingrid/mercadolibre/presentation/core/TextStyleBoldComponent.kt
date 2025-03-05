package com.ingrid.mercadolibre.presentation.core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ingrid.mercadolibre.ui.theme.BlackML

@Composable
fun TextStyleBoldComponent(
    modifier: Modifier,
    textStyleBold: String = "",
    textStyleSimple: String = "",
    fontSize: TextUnit = 14.sp,
    textAlign: TextAlign =  TextAlign.Start,
    tint : Color = BlackML,
    isFirstSimpleText : Boolean = false,
    paddingHorizontal: Dp = 0.dp,
    paddingVertical: Dp = 0.dp,
) {
    Text(
        text = buildAnnotatedString {
            if (isFirstSimpleText) {
                if (textStyleSimple.isNotEmpty()) {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize =fontSize,
                        )
                    ) {
                        append(textStyleSimple)
                    }
                }
                if (textStyleBold.isNotEmpty()) {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = fontSize,
                        )
                    ) {
                        append(textStyleBold)

                    }
                }
            } else {
                if (textStyleBold.isNotEmpty()) {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = fontSize,
                        )
                    ) {
                        append(textStyleBold)

                    }
                }
                if (textStyleSimple.isNotEmpty()) {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = fontSize,
                        )
                    ) {
                        append(textStyleSimple)
                    }
                }
            }
        },
        color = tint,
        textAlign = textAlign,
        maxLines = 3,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = paddingHorizontal)
            .padding(bottom = paddingVertical)
    )
}
package com.ingrid.mercadolibre.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Font
import com.ingrid.mercadolibre.R

val robotoBold = FontFamily(
    Font(R.font.roboto_bold, weight = FontWeight.W700),
    Font(R.font.roboto_bold, weight = FontWeight.W600),
    Font(R.font.roboto_bold, weight = FontWeight.W500),
    Font(R.font.roboto_bold, weight = FontWeight.W400),
)

val robotMedium = FontFamily(
    Font(R.font.roboto_medium, weight = FontWeight.W700),
    Font(R.font.roboto_medium, weight = FontWeight.W600),
    Font(R.font.roboto_medium, weight = FontWeight.W500),
    Font(R.font.roboto_medium, weight = FontWeight.W400),
)
val robotoRegular = FontFamily(
    Font(R.font.roboto_regular, weight = FontWeight.W700),
    Font(R.font.roboto_regular, weight = FontWeight.W600),
    Font(R.font.roboto_regular, weight = FontWeight.W500),
    Font(R.font.roboto_regular, weight = FontWeight.W400),
)

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(fontFamily = robotoBold),
    displayMedium = TextStyle(fontFamily = robotoBold),
    displaySmall = TextStyle(fontFamily = robotoBold),
    headlineLarge = TextStyle(fontFamily = robotoBold),
    headlineMedium = TextStyle(fontFamily = robotMedium),
    headlineSmall = TextStyle(fontFamily = robotMedium),
    titleLarge = TextStyle(fontFamily = robotMedium),
    titleMedium = TextStyle(fontFamily = robotMedium),
    titleSmall = TextStyle(fontFamily = robotoRegular),
    bodyLarge = TextStyle(fontFamily = robotoRegular),
    bodyMedium = TextStyle(fontFamily = robotoRegular),
    bodySmall = TextStyle(fontFamily = robotoRegular)
)
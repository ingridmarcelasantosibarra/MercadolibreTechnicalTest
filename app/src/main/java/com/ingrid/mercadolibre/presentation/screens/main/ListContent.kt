package com.ingrid.mercadolibre.presentation.screens.main

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.ingrid.mercadolibre.R
import com.ingrid.mercadolibre.data.model.search.Result
import com.ingrid.mercadolibre.presentation.core.TextStyleBoldComponent
import com.ingrid.mercadolibre.ui.theme.BlackML
import com.ingrid.mercadolibre.ui.theme.GrayML
import com.ingrid.mercadolibre.ui.theme.GreenML
import com.ingrid.mercadolibre.util.Constants.LOGISTIC_TYPE
import com.ingrid.mercadolibre.util.Util.toFormatCurrencyCOP

@Composable
fun ListContent(
    context: Context,
    products: Result,
    onClick: (Result) -> Unit
) {
    val url = products.thumbnail
    val placeholderImage = R.drawable.ic_default_image

    val imageRequest = ImageRequest.Builder(context)
        .data(url)
        .placeholder(placeholderImage)
        .error(placeholderImage)
        .transformations(RoundedCornersTransformation())
        .build()
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick(products)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier.weight(0.5f),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = GrayML.copy(alpha = 0.5f)
            )
        ) {
            AsyncImage(
                model = imageRequest,
                contentDescription = products.title,
                modifier = Modifier.size(140.dp),
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            TextStyleBoldComponent(
                textStyleSimple = products.title,
                fontSize = 14.sp,
                modifier = Modifier,
                textAlign = TextAlign.Center,
                tint = BlackML
            )
            TextStyleBoldComponent(
                textStyleSimple = products.price.toFormatCurrencyCOP(),
                fontSize = 14.sp,
                modifier = Modifier,
                textAlign = TextAlign.Center,
                tint = BlackML
            )
            AnimatedVisibility(visible = products.shipping.free_shipping) {
                TextStyleBoldComponent(
                    textStyleSimple = stringResource(R.string.text_free_shipping),
                    fontSize = 10.sp,
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                    tint = GreenML
                )
            }
            AnimatedVisibility(visible = products.shipping.logistic_type == LOGISTIC_TYPE) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .background(GreenML.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = stringResource(R.string.text_arrive_tomorrow),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = GreenML,
                            fontSize = 10.sp,
                        )
                    )
                }
            }
            TextStyleBoldComponent(
                textStyleSimple = products.seller_address.state.name,
                fontSize = 10.sp,
                modifier = Modifier,
                textAlign = TextAlign.Center,
                tint = GrayML
            )

        }
    }
    HorizontalDivider(
        thickness = 1.dp,
        color = GrayML
    )
}
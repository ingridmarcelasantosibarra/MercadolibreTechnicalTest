package com.ingrid.mercadolibre.presentation.screens.main

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.ingrid.mercadolibre.R
import com.ingrid.mercadolibre.data.model.search.Result
import com.ingrid.mercadolibre.ui.theme.gray10

@Composable
fun ListContent(
    context: Context,
    products: Result,
) {
    val url = products.thumbnail
    val placeholderImage = R.drawable.ic_default_image

    val imageRequest = ImageRequest.Builder(context)
        .data(url)
        .placeholder(placeholderImage)
        .error(placeholderImage)
        .transformations(RoundedCornersTransformation())
        .build()

    Spacer(modifier = Modifier.height(8.dp))
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {

            },
        verticalAlignment = Alignment.Top
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = gray10.copy(alpha = 0.5f)
            )
        ) {
            AsyncImage(
                model = imageRequest,
                contentDescription = "",
                modifier = Modifier.sizeIn(30.dp)
            )
        }
        Column {
            Text(
                text = products.title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Black,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight.Normal
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
            )
            Text(
                text = products.price.toString() ?: "",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Black,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight.Normal
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
            /*AnimatedVisibility(visible = products.shipping.free_shipping) {
                Text(
                    text = stringResource(R.string.free_shipping),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Green,
                        lineHeight = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
            AnimatedVisibility(visible = products.shipping.logistic_type == LOGISTIC_TYPE) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .background(Green.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = stringResource(R.string.arrive_tomorrow),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Green,
                            fontSize = 10.sp,
                        )
                    )
                }
            }*/
            Text(
                text = products.seller_address.state.name,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Black,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight.Normal
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}
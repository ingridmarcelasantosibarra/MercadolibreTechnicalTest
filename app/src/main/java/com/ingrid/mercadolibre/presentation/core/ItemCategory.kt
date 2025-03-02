package com.ingrid.mercadolibre.presentation.core

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ingrid.mercadolibre.data.model.categories.Category
import com.ingrid.mercadolibre.presentation.IconMap.iconMap
import com.ingrid.mercadolibre.ui.theme.yellow

@Composable
fun ItemCategory(item: Category, modifier: Modifier = Modifier, onClickItem : (Category) -> Unit) {
    Card(
        modifier = modifier
            .padding(
                start = 0.dp,
                end = 0.dp,
                bottom = 8.dp,
                top = 8.dp
            )
            .clickable {
                onClickItem(item)
            },
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 8.dp,
                    top = 8.dp
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(5.dp)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(yellow),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = iconMap[item.id] ?: Icons.Outlined.Delete,
                    contentDescription = item.name,
                    tint = Color.Black.copy(alpha = 0.5f)
                )
            }
            Text(
                text = item.name,
                style = MaterialTheme.typography.displayMedium.copy(
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    color = MaterialTheme.colorScheme.primary,
                ),
                maxLines = 1,
                textAlign = TextAlign.Start,
                modifier = modifier
                    .weight(1f).padding(horizontal = 16.dp)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun IItemCategoryPreview(modifier: Modifier = Modifier) {
    ItemCategory(
        onClickItem = {},
        item = Category(
            id = "test",
            name = "Cuenta ahorros"
        )
    )
}

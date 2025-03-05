package com.ingrid.mercadolibre.presentation.screens.detail.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.WorkspacePremium
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ingrid.mercadolibre.R
import com.ingrid.mercadolibre.data.model.detail.DetailResponse
import com.ingrid.mercadolibre.data.model.detail.SellerAddress
import com.ingrid.mercadolibre.ui.theme.GreenML


@Composable
fun SellerInfo(
    location: SellerAddress,
    detail: DetailResponse
) {
    Text(
        modifier = Modifier.padding(top = 20.dp),
        text = stringResource(R.string.seller_info),
        style = MaterialTheme.typography.bodyLarge.copy(
            color = Black
        )
    )
    CustomRowSeller(
        text1 = stringResource(R.string.location),
        text2 = location.city.name,
        icon = Icons.Outlined.Place
    )
    CustomRowSeller(
        color = GreenML,
        text1 = stringResource(R.string.level),
        text2 = stringResource(R.string.score),
        icon = Icons.Outlined.WorkspacePremium
    )
    RowScore(detail = detail)
   // ScoreSeller(detail = detail)
}

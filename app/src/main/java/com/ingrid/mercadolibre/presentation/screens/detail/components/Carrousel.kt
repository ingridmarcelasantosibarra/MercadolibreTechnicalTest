package com.ingrid.mercadolibre.presentation.screens.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.ingrid.mercadolibre.R
import com.ingrid.mercadolibre.presentation.core.TextStyleBoldComponent
import com.ingrid.mercadolibre.ui.theme.BlackML
import com.ingrid.mercadolibre.ui.theme.GrayML

@Composable
fun Carrousel(
    pages: Int,
    pictures: MutableList<String>,
) {
    val context = LocalContext.current
    val placeholderImage = R.drawable.ic_default_image
    val pagerState = rememberPagerState(pageCount = { pages })
    Box(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.TopStart
    ) {
        HorizontalPager(
            pageSize = PageSize.Fill,
            beyondViewportPageCount = pages,
            state = pagerState
        ) { page ->
            val imageRequest = ImageRequest.Builder(context)
                .data(pictures[page])
                .error(placeholderImage)
                .build()
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                SubcomposeAsyncImage(
                    model = imageRequest,
                    contentDescription = "",
                    modifier = Modifier.size(400.dp),
                    contentScale = ContentScale.Fit,
                    loading = {

                    }

                )
            }
        }
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            contentColor = Color.Transparent,
            containerColor = Color.Transparent,
            modifier = Modifier.width(100.dp),
            indicator = { Box(Modifier.size(0.dp)) },
            divider = { Box(Modifier.size(0.dp)) }
        ) {
            Box(
                modifier = Modifier
                    .padding(20.dp)
                    .background(color = GrayML, shape = CircleShape)
            ) {
                TextStyleBoldComponent(
                    paddingHorizontal = 8.dp,
                    paddingVertical = 4.dp,
                    textStyleSimple =  "${pagerState.currentPage + 1} / $pages",
                    fontSize = 12.sp,
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                    tint = BlackML
                )
            }
        }
    }
}
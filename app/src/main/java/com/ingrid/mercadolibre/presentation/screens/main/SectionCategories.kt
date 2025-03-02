package com.ingrid.mercadolibre.presentation.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ingrid.mercadolibre.data.model.categories.Category
import com.ingrid.mercadolibre.presentation.core.ItemCategory

@Composable
fun SectionCategories(
    itemsList: List<Category>,
    modifier: Modifier = Modifier,
    rows: Int = 1,
    onClickItem : (Category) -> Unit
) {
    val height = 55.dp
    val totalHeight = height * itemsList.size
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = rows),
            contentPadding = PaddingValues(horizontal = 0.dp),
            modifier = modifier.height(totalHeight)
        ) {
            items(itemsList) { item ->
                ItemCategory(item = item, modifier = modifier, onClickItem = {

                })
            }
        }
    }


}
package com.ingrid.mercadolibre.domain.repository

import com.ingrid.mercadolibre.data.model.description.DescriptionResponse
import com.ingrid.mercadolibre.data.model.detail.DetailResponse
import com.ingrid.mercadolibre.data.model.categories.CategoriesResponse
import com.ingrid.mercadolibre.data.model.search.SearchResponse

interface MercadoLibreRepository {

    suspend fun getDetails(id: String): Result<DetailResponse>

    suspend fun getDescription(id: String): Result<DescriptionResponse>

    suspend fun getCategories(): Result<CategoriesResponse>

    suspend fun getBySearch(product: String, limit: Int, offset: Int): Result<SearchResponse>
}
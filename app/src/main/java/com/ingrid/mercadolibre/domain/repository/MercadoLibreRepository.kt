package com.ingrid.mercadolibre.domain.repository

import com.ingrid.mercadolibre.data.model.description.DescriptionResponse
import com.ingrid.mercadolibre.data.model.detail.DetailResponse
import com.ingrid.mercadolibre.data.model.categories.CategoriesResponse

interface MercadoLibreRepository {

    suspend fun getDetails(id: String): Result<DetailResponse>

    suspend fun getDescription(id: String): Result<DescriptionResponse>

    suspend fun getCategories(): Result<CategoriesResponse>
}
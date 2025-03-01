package com.ingrid.mercadolibre.data.repositoryImpl

import com.ingrid.mercadolibre.data.model.description.DescriptionResponse
import com.ingrid.mercadolibre.data.model.detail.DetailResponse
import com.ingrid.mercadolibre.data.model.categories.CategoriesResponse
import com.ingrid.mercadolibre.data.model.search.SearchResponse
import com.ingrid.mercadolibre.data.remote.MercadoLibreApi
import com.ingrid.mercadolibre.domain.repository.MercadoLibreRepository

class MercadoLibreRepositoryImpl(
    private val api: MercadoLibreApi,
) : MercadoLibreRepository {

    override suspend fun getDetails(id: String): Result<DetailResponse> {
        return try {
            val result = api.getDetails(id)
            Result.success(result)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override suspend fun getDescription(id: String): Result<DescriptionResponse> {
        return try {
            val result = api.getDescription(id)
            Result.success(result)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override suspend fun getCategories(): Result<CategoriesResponse> {
        return try {
            val result = api.getCategories()
            Result.success(result)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override suspend fun getBySearch(
        product: String,
        limit: Int,
        offset: Int
    ): Result<SearchResponse> {
        return try {
            val result = api.getBySearch(product, limit, offset)
            Result.success(result)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
package com.ingrid.mercadolibre.data.remote

import com.ingrid.mercadolibre.data.model.description.DescriptionResponse
import com.ingrid.mercadolibre.data.model.detail.DetailResponse
import com.ingrid.mercadolibre.data.model.categories.CategoriesResponse
import com.ingrid.mercadolibre.util.Constants.CATEGORIES_END_POINT
import com.ingrid.mercadolibre.util.Constants.DESCRIPTION_END_POINT
import com.ingrid.mercadolibre.util.Constants.DETAILS_END_POINT
import com.ingrid.mercadolibre.util.Constants.KEY_ID
import retrofit2.http.GET
import retrofit2.http.Path

interface MercadoLibreApi {

    @GET(DETAILS_END_POINT)
    suspend fun getDetails(
        @Path(KEY_ID) id: String,
    ): DetailResponse

    @GET(DESCRIPTION_END_POINT)
    suspend fun getDescription(
        @Path(KEY_ID) id: String,
    ): DescriptionResponse

    @GET(CATEGORIES_END_POINT)
    suspend fun getCategories(): CategoriesResponse
}
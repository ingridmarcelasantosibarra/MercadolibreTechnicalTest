package com.ingrid.mercadolibre.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ingrid.mercadolibre.data.model.search.Result
import com.ingrid.mercadolibre.data.model.search.SearchResponse
import com.ingrid.mercadolibre.domain.useCases.GetSearchUseCase

class SearchPagingSource(
    private val useCase: GetSearchUseCase,
    private val product: String
) : PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val page = params.key ?: 1
            var response = SearchResponse()

            useCase(product = product, offset = page, limit = params.loadSize)
                .onSuccess { searchResponse ->

                    response = searchResponse
                }.onFailure {
                    return@onFailure
                }
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page - 50,
                nextKey = if (response.results.isNotEmpty()) page + 50 else null
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
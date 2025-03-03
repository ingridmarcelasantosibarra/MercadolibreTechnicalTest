package com.ingrid.mercadolibre.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ingrid.mercadolibre.data.model.search.Result
import com.ingrid.mercadolibre.data.model.search.SearchResponse
import com.ingrid.mercadolibre.domain.repository.MercadoLibreRepository
import com.ingrid.mercadolibre.domain.useCases.GetSearchUseCase

class SearchPagingSource(
    private val mercadoLibreRepository: MercadoLibreRepository,
    private val product: String
) : PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val page = params.key ?: 0
            val offset = page * params.loadSize

            val response = mercadoLibreRepository.getBySearch(
                product = product,
                limit = params.loadSize,
                offset = offset
            ).getOrThrow()

            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.results.isEmpty()) null else page + 1
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

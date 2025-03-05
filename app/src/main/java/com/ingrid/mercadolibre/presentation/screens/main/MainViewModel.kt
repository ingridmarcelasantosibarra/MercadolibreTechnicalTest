package com.ingrid.mercadolibre.presentation.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ingrid.mercadolibre.data.model.categories.Category
import com.ingrid.mercadolibre.data.model.search.Result
import com.ingrid.mercadolibre.data.pagingSource.SearchPagingSource
import com.ingrid.mercadolibre.domain.repository.MercadoLibreRepository
import com.ingrid.mercadolibre.domain.useCases.MercadoLibreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mercadoLibreUseCases: MercadoLibreUseCases,
    private val mercadoLibreRepository: MercadoLibreRepository
) : ViewModel() {
    var productBySearch by mutableStateOf("")
        private set
    var listCategories = mutableStateListOf<Category>()
        private set
    var isLoading by mutableStateOf(false)
        private set
    var searchFlow: Flow<PagingData<Result>> = emptyFlow()
        private set

    init {
        getCategories()
    }


    fun searchProducts(product: String) {
        isLoading = true
        searchFlow = Pager(
            config = PagingConfig(
                pageSize = 50,
                initialLoadSize = 50,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SearchPagingSource(
                    mercadoLibreRepository = mercadoLibreRepository,
                    product = product,
                )
            }
        ).flow.cachedIn(viewModelScope).also {
            isLoading = false
        }
    }

    fun clearSearch() {
        searchFlow = emptyFlow()
    }


     fun getCategories() {
        viewModelScope.launch {
            isLoading = true
            mercadoLibreUseCases.getCategoriesUseCase()
                .onSuccess { categoriesResponse ->
                    isLoading = false
                    categoriesResponse.categories.forEach {
                        listCategories.add(it)
                    }
                }.onFailure {
                    isLoading = false


                    return@onFailure
                }
        }
    }
}
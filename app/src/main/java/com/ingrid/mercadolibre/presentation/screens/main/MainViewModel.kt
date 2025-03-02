package com.ingrid.mercadolibre.presentation.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ingrid.mercadolibre.data.model.categories.Category
import com.ingrid.mercadolibre.domain.useCases.MercadoLibreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mercadoLibreUseCases: MercadoLibreUseCases
) : ViewModel() {

    var listCategories = mutableStateListOf<Category>()
        private set
    var isLoading by mutableStateOf(false)
        private set

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            isLoading = true
            mercadoLibreUseCases.getCategoriesUseCase()
                .onSuccess { categoriesResponse ->
                    isLoading = false
                    categoriesResponse.categories.forEach {
                        listCategories.add(it)
                    }
                    println("OOJOOO $listCategories")
                }.onFailure {
                    isLoading = false


                    return@onFailure
                }
        }
    }
}
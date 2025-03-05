package com.ingrid.mercadolibre.presentation.screens.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ingrid.mercadolibre.data.model.description.DescriptionResponse
import com.ingrid.mercadolibre.data.model.detail.DetailResponse
import com.ingrid.mercadolibre.domain.useCases.MercadoLibreUseCases
import com.ingrid.mercadolibre.util.Constants.ITEM_CONDITION
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val mercadoLibreUseCases: MercadoLibreUseCases,
) : ViewModel() {
    var detail by mutableStateOf(DetailResponse())
        private set
    var pictures = mutableStateListOf<String>()
        private set
    var itemCondition = ""
        private set
    var isLoading by mutableStateOf(false)
        private set
    var description by mutableStateOf(DescriptionResponse())
        private set


    fun getDetails(id: String) {
        viewModelScope.launch {
            isLoading = true
            mercadoLibreUseCases.getDetailUseCase(id)
                .onSuccess { detailResponse ->
                    isLoading = false
                    detail = detailResponse
                    pictures.addAll(detailResponse.pictures.map { it.url })
                    detailResponse.attributes.forEach { attribute ->
                        if (attribute.id == ITEM_CONDITION) {
                            itemCondition = attribute.value_name
                        }
                    }
                }.onFailure {
                    isLoading = false
                    val errorCode = it.message ?: ""
                    /*_eventUi.send(
                        DetailEventUi.ShowError(
                            title = "Lo sentimos!",
                            description = "Estamos trabajando para brindarte un mejor servicio."
                        )
                    )*/
                    Log.e("error detail", errorCode)
                    return@onFailure
                }
        }
    }

    fun getDescription(id: String) {
        viewModelScope.launch {
            isLoading = true
            mercadoLibreUseCases.getDescriptionUseCase(id)
                .onSuccess { descriptionResponse ->
                    isLoading = false
                    description = descriptionResponse
                }.onFailure {
                    isLoading = false

                    Log.e("error description", it.message ?: "")
                    return@onFailure
                }
        }
    }

    fun getQuantity(detail: DetailResponse): Int {
        return when (detail.sold_quantity) {
            in 0..20 -> 0
            in 21..40 -> 1
            in 41..60 -> 2
            in 61..80 -> 3
            else -> 4
        }
    }
}
package com.ingrid.mercadolibre.domain.useCases

data class MercadoLibreUseCases(
    val getDetailUseCase: GetDetailUseCase,
    val getDescriptionUseCase: GetDescriptionUseCase,
    val getCategoriesUseCase: GetCategoriesUseCase,
    val getSearchUseCase: GetSearchUseCase
)
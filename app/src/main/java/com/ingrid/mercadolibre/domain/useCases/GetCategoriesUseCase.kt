package com.ingrid.mercadolibre.domain.useCases

import com.ingrid.mercadolibre.domain.repository.MercadoLibreRepository

class GetCategoriesUseCase(
    private val mercadoLibreRepository: MercadoLibreRepository
) {
    suspend operator fun invoke() = mercadoLibreRepository.getCategories()
}
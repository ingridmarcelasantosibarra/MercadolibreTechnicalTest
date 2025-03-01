package com.ingrid.mercadolibre.domain.useCases

import com.ingrid.mercadolibre.domain.repository.MercadoLibreRepository

class GetDetailUseCase(
    private val mercadoLibreRepository: MercadoLibreRepository
) {
    suspend operator fun invoke(id: String) = mercadoLibreRepository.getDetails(id)
}
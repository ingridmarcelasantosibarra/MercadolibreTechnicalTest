package com.ingrid.mercadolibre.domain.useCases

import com.ingrid.mercadolibre.domain.repository.MercadoLibreRepository

class GetSearchUseCase(
    private val mercadoLibreRepository: MercadoLibreRepository
) {
    suspend operator fun invoke(product: String, limit: Int, offset: Int) =
        mercadoLibreRepository.getBySearch(
            product = product,
            limit = limit,
            offset = offset
        )
}
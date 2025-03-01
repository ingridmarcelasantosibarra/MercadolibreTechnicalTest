package com.ingrid.mercadolibre.data.model.search

data class Prices(
    val id: String = "",
    val payment_method_prices: List<Any> = emptyList(),
    val presentation: Presentation = Presentation(),
    val prices: List<Price> = emptyList(),
    val purchase_discounts: List<Any> = emptyList(),
    val reference_prices: List<ReferencePrice> = emptyList()
)
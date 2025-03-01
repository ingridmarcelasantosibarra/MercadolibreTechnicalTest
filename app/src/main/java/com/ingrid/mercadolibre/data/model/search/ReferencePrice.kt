package com.ingrid.mercadolibre.data.model.search

data class ReferencePrice(
    val amount: Double,
    val currency_id: String,
    val exchange_rate_context: String,
    val id: String,
    val last_updated: String,
    val tags: List<Any>,
    val type: String
)
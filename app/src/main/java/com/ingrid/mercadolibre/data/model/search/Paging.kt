package com.ingrid.mercadolibre.data.model.search

data class Paging(
    val limit: Int = 0,
    val offset: Int = 0,
    val primary_results: Int = 0,
    val total: Int = 0
)
package com.ingrid.mercadolibre.data.model.search

data class Shipping(
    val free_shipping: Boolean = false,
    val logistic_type: Any = Any(),
    val mode: String = "",
    val store_pick_up: Boolean = false,
    val tags: List<Any> = emptyList()
)
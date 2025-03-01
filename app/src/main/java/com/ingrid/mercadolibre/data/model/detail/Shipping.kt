package com.ingrid.mercadolibre.data.model.detail


data class Shipping(
    val dimensions: Any = Any(),
    val free_shipping: Boolean = false,
    val local_pick_up: Boolean = false,
    val logistic_type: String = "",
    val methods: List<Any> = emptyList(),
    val mode: String = "",
    val store_pick_up: Boolean = false,
    val tags: List<String> = emptyList()
)
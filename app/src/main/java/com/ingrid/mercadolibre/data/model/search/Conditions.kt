package com.ingrid.mercadolibre.data.model.search

data class Conditions(
    val context_restrictions: List<Any>,
    val eligible: Boolean,
    val end_time: Any,
    val start_time: Any
)
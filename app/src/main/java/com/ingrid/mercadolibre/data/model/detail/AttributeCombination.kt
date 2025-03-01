package com.ingrid.mercadolibre.data.model.detail

data class AttributeCombination(
    val id: String,
    val name: String,
    val value_id: String,
    val value_name: String,
    val value_struct: Any,
    val values: List<Value>
)
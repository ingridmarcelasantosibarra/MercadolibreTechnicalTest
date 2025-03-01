package com.ingrid.mercadolibre.data.model.detail

data class Variation(
    val attribute_combinations: List<AttributeCombination>,
    val available_quantity: Int,
    val catalog_product_id: Any,
    val id: Long,
    val picture_ids: List<String>,
    val price: Int,
    val sale_terms: List<Any>,
    val sold_quantity: Int
)
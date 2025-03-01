package com.ingrid.mercadolibre.data.model.search

data class Transactions(
    val canceled: Int = 0,
    val completed: Int = 0,
    val period: String = "",
    val ratings: Ratings = Ratings(),
    val total: Int = 0
)
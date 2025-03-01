package com.ingrid.mercadolibre.data.model.detail

data class SearchLocation(
    val city: City = City(),
    val state: State = State()
)
package com.ingrid.mercadolibre.data.model.detail


data class SellerAddress(
    val city: City = City(),
    val country: Country = Country(),
    val id: Int = 0,
    val search_location: SearchLocation = SearchLocation(),
    val state: State = State()
)
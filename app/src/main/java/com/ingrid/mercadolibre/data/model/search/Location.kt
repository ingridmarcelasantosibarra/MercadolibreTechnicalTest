package com.ingrid.mercadolibre.data.model.search

data class Location(
    val address_line: String = "",
    val city: City = City(),
    val country: Country = Country(),
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val neighborhood: Neighborhood = Neighborhood(),
    val state: State = State(),
    val subneighborhood: Any = Any(),
    val zip_code: String = ""
)
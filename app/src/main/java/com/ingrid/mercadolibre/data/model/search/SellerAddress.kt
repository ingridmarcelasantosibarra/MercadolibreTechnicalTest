package com.ingrid.mercadolibre.data.model.search


data class SellerAddress(
    val address_line: String = "",
    val city: City = City(),
    val comment: String = "",
    val country: Country = Country(),
    val id: String = "",
    val latitude: String = "",
    val longitude: String = "",
    val state: State = State(),
    val zip_code: String = ""
)
package com.ingrid.mercadolibre.data.model.categories

data class CategoriesResponse(
    val categories: List<Category> = emptyList(),
    val channels: List<String> = emptyList(),
    val country_id: String = "",
    val currencies: List<Currency> = emptyList(),
    val default_currency_id: String = "",
    val id: String = "",
    val immediate_payment: String = "",
    val mercadopago_version: Int = 0,
    val name: String = "",
    val payment_method_ids: List<String> = emptyList(),
    val sale_fees_mode: String = "",
    val settings: Settings = Settings()
)
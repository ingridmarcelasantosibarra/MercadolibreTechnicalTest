package com.ingrid.mercadolibre.data.model.search

data class SearchResponse(
    val available_filters: List<AvailableFilter> = emptyList(),
    val available_sorts: List<AvailableSort> = emptyList(),
    val country_default_time_zone: String = "",
    val filters: List<Filter> = emptyList(),
    val paging: Paging = Paging(),
    val query: String = "",
    val results: List<Result> = emptyList(),
    val site_id: String = "",
    val sort: Sort = Sort()
)
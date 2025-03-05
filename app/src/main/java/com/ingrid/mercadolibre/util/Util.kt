package com.ingrid.mercadolibre.util

import com.ingrid.mercadolibre.data.model.detail.DetailResponse
import java.text.NumberFormat
import java.util.Locale

object Util {

    fun Int.toFormatCurrencyCOP(): String {
        val formatter = NumberFormat.getNumberInstance(Locale("es", "CO"))
        return "$ ${formatter.format(this)}"
    }

    fun getQuantity(detail: DetailResponse): Int {
        return when (detail.sold_quantity) {
            in 0..20 -> 0
            in 21..40 -> 1
            in 41..60 -> 2
            in 61..80 -> 3
            else -> 4
        }
    }
}
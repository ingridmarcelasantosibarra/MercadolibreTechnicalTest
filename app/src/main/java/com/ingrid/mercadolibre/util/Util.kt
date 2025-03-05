package com.ingrid.mercadolibre.util

import com.ingrid.mercadolibre.data.model.detail.DetailResponse
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
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
    fun readJsonFile(fileName: String): String {
        var inputStream: InputStream? = null
        try {
            inputStream = javaClass.classLoader?.getResourceAsStream(fileName)
            val builder = StringBuilder()
            val reader = BufferedReader(InputStreamReader(inputStream))
            var str: String? = reader.readLine()
            while (str != null) {
                builder.append(str)
                str = reader.readLine()
            }
            return builder.toString()
        } finally {
            inputStream?.close()
        }
    }
}
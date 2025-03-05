package com.ingrid.mercadolibre.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object Splash

@Serializable
object Main

@Serializable
data class Detail(val id: String, val price: Int, val title: String)
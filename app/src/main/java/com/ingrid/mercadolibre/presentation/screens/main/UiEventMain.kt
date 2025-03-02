package com.ingrid.mercadolibre.presentation.screens.main

import com.ingrid.mercadolibre.R

sealed class UiEventMain {
    data class ShowModal(
        val title: String,
        val showModal: Boolean,
        val description: String = "",
        val descriptionBold: String = "",
        val icon: Int = R.drawable.mercado_libre,
        val textButtonSecondary: String = "",
        val textButton: String,
        val enableSecondaryButton: Boolean = false,
        val enableButton: Boolean = false,
    ) : UiEventMain()
}
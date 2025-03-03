package com.ingrid.mercadolibre.domain.di

import com.ingrid.mercadolibre.domain.repository.MercadoLibreRepository
import com.ingrid.mercadolibre.domain.useCases.GetCategoriesUseCase
import com.ingrid.mercadolibre.domain.useCases.GetDescriptionUseCase
import com.ingrid.mercadolibre.domain.useCases.GetDetailUseCase
import com.ingrid.mercadolibre.domain.useCases.GetSearchUseCase
import com.ingrid.mercadolibre.domain.useCases.MercadoLibreUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @ViewModelScoped
    @Provides
    fun provideMercadoLibreUseCases(
        mercadoLibreRepository: MercadoLibreRepository 
    ): MercadoLibreUseCases {
        return MercadoLibreUseCases(
            getDescriptionUseCase = GetDescriptionUseCase(mercadoLibreRepository),
            getDetailUseCase = GetDetailUseCase(mercadoLibreRepository),
            getCategoriesUseCase = GetCategoriesUseCase(mercadoLibreRepository),
            getSearchUseCase = GetSearchUseCase(mercadoLibreRepository)
        )
    }
}
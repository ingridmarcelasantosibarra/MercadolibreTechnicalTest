package com.ingrid.mercadolibre.data.di

import com.ingrid.mercadolibre.data.remote.MercadoLibreApi
import com.ingrid.mercadolibre.data.repositoryImpl.MercadoLibreRepositoryImpl
import com.ingrid.mercadolibre.domain.repository.MercadoLibreRepository
import com.ingrid.mercadolibre.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideOKHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        ).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideMercadoLibreApi(retrofit: Retrofit): MercadoLibreApi = retrofit.create()

    @Provides
    @Singleton
    fun provideMercadoLibreRepository(api: MercadoLibreApi): MercadoLibreRepository = MercadoLibreRepositoryImpl(api)
}
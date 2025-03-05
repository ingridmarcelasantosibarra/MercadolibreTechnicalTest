package com.ingrid.mercadolibre.domain.useCase

import com.google.common.truth.Truth
import com.ingrid.mercadolibre.data.model.categories.CategoriesResponse
import com.ingrid.mercadolibre.data.model.description.DescriptionResponse
import com.ingrid.mercadolibre.data.model.detail.DetailResponse
import com.ingrid.mercadolibre.data.model.search.SearchResponse
import com.ingrid.mercadolibre.domain.repository.MercadoLibreRepository
import com.ingrid.mercadolibre.domain.useCases.GetCategoriesUseCase
import com.ingrid.mercadolibre.domain.useCases.GetDescriptionUseCase
import com.ingrid.mercadolibre.domain.useCases.GetDetailUseCase
import com.ingrid.mercadolibre.domain.useCases.GetSearchUseCase
import com.ingrid.mercadolibre.domain.useCases.MercadoLibreUseCases
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetCategoriesUseCaseTest {
    private val mercadoLibreRepository = mockk<MercadoLibreRepository>()
    private lateinit var getCategoriesUseCase: GetCategoriesUseCase
    private lateinit var getBySearchUseCase: GetSearchUseCase
    private lateinit var getDescriptionUseCase: GetDescriptionUseCase
    private lateinit var getDetailUseCase: GetDetailUseCase
    private lateinit var mercadoLibreUseCases: MercadoLibreUseCases

    @Before
    fun setup() {
        getCategoriesUseCase = GetCategoriesUseCase(mercadoLibreRepository)
        getBySearchUseCase = GetSearchUseCase(mercadoLibreRepository)
        getDescriptionUseCase = GetDescriptionUseCase(mercadoLibreRepository)
        getDetailUseCase = GetDetailUseCase(mercadoLibreRepository)
        mercadoLibreUseCases = MercadoLibreUseCases(
            getCategoriesUseCase = getCategoriesUseCase,
            getSearchUseCase = getBySearchUseCase,
            getDescriptionUseCase = getDescriptionUseCase,
            getDetailUseCase = getDetailUseCase
        )
    }

    @Test
    fun `when get categories respond success`() {
        runBlocking {
            val categoriesResponse = mockk<CategoriesResponse>()
            coEvery {
                mercadoLibreRepository.getCategories()
            } returns Result.success(categoriesResponse)

            val resultado = getCategoriesUseCase.invoke()

            coVerify {
                mercadoLibreRepository.getCategories()
            }
            Truth.assertThat(resultado.isSuccess).isTrue()
            confirmVerified(categoriesResponse)
        }
    }

    @Test
    fun `when get categories respond failure`() {
        runBlocking {
            coEvery {
                mercadoLibreRepository.getCategories()
            } returns Result.failure(Throwable())

            val resultado = getCategoriesUseCase.invoke()

            coVerify {
                mercadoLibreRepository.getCategories()
            }
            Truth.assertThat(resultado.isFailure).isTrue()
        }
    }

    @Test
    fun `when get by search respond success`() {
        runBlocking {
            val searchResponse = mockk<SearchResponse>()
            coEvery {
                mercadoLibreRepository.getBySearch(
                    limit = 1,
                    offset = 0,
                    product = "product"
                )
            } returns Result.success(searchResponse)

            val resultado = getBySearchUseCase.invoke(
                limit = 1,
                offset = 0,
                product = "product"
            )

            coVerify {
                mercadoLibreRepository.getBySearch(
                    limit = 1,
                    offset = 0,
                    product = "product"
                )
            }
            Truth.assertThat(resultado.isSuccess).isTrue()
            confirmVerified(searchResponse)
        }
    }

    @Test
    fun `when get by search respond failure`() {
        runBlocking {
            coEvery {
                mercadoLibreRepository.getBySearch(
                    limit = 1,
                    offset = 0,
                    product = "product"
                )
            } returns Result.failure(Throwable())

            val resultado = getBySearchUseCase.invoke(
                limit = 1,
                offset = 0,
                product = "product"
            )

            coVerify {
                mercadoLibreRepository.getBySearch(
                    limit = 1,
                    offset = 0,
                    product = "product"
                )
            }
            Truth.assertThat(resultado.isFailure).isTrue()
        }
    }

    @Test
    fun `when get description respond success`() {
        runBlocking {
            val descriptionResponse = mockk<DescriptionResponse>()
            coEvery {
                mercadoLibreRepository.getDescription(id = "id")
            } returns Result.success(descriptionResponse)

            val resultado = getDescriptionUseCase.invoke(id = "id")

            coVerify {
                mercadoLibreRepository.getDescription(id = "id")
            }
            Truth.assertThat(resultado.isSuccess).isTrue()
            confirmVerified(descriptionResponse)
        }
    }

    @Test
    fun `when get description respond failure`() {
        runBlocking {
            coEvery {
                mercadoLibreRepository.getDescription(id = "id")
            } returns Result.failure(Throwable())

            val resultado = getDescriptionUseCase.invoke(id = "id")

            coVerify {
                mercadoLibreRepository.getDescription(id = "id")
            }
            Truth.assertThat(resultado.isFailure).isTrue()
        }
    }

    @Test
    fun `when get detail respond success`() {
        runBlocking {
            val detailResponse = mockk<DetailResponse>()
            coEvery {
                mercadoLibreRepository.getDetails(id = "id")
            } returns Result.success(detailResponse)

            val resultado = getDetailUseCase.invoke(id = "id")

            coVerify {
                mercadoLibreRepository.getDetails(id = "id")
            }
            Truth.assertThat(resultado.isSuccess).isTrue()
            confirmVerified(detailResponse)
        }
    }

    @Test
    fun `when get detail respond failure`() {
        runBlocking {
            coEvery {
                mercadoLibreRepository.getDetails(id = "id")
            } returns Result.failure(Throwable())

            val resultado = getDetailUseCase.invoke(id = "id")

            coVerify {
                mercadoLibreRepository.getDetails(id = "id")
            }
            Truth.assertThat(resultado.isFailure).isTrue()
        }
    }

    @After
    fun tearDown() {
        confirmVerified(mercadoLibreRepository)
    }
}
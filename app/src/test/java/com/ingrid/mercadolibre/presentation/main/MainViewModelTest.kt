package com.ingrid.mercadolibre.presentation.main

import com.ingrid.mercadolibre.data.model.categories.CategoriesResponse
import com.ingrid.mercadolibre.domain.repository.MercadoLibreRepository
import com.ingrid.mercadolibre.domain.useCases.GetCategoriesUseCase
import com.ingrid.mercadolibre.domain.useCases.GetDescriptionUseCase
import com.ingrid.mercadolibre.domain.useCases.GetDetailUseCase
import com.ingrid.mercadolibre.domain.useCases.GetSearchUseCase
import com.ingrid.mercadolibre.domain.useCases.MercadoLibreUseCases
import com.ingrid.mercadolibre.presentation.screens.main.MainViewModel
import com.ingrid.mercadolibre.utils.CoroutinesRules
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get: Rule
    var coroutinesRules = CoroutinesRules()

    private lateinit var mainViewModel: MainViewModel

    private lateinit var meliUseCases: MercadoLibreUseCases

    private lateinit var mercadoLibreRepository: MercadoLibreRepository


    private val getBySearchUseCase: GetSearchUseCase = mockk(relaxed = true)
    private val getCategoriesUseCase: GetCategoriesUseCase = mockk(relaxed = true)
    private val getDescriptionUseCase: GetDescriptionUseCase = mockk(relaxed = true)
    private val getDetailUseCase: GetDetailUseCase = mockk(relaxed = true)
    private val scope = TestScope(coroutinesRules.testDispatcher)

    @Before
    fun setUp() {
        mercadoLibreRepository = mockk()
        meliUseCases = MercadoLibreUseCases(
            getSearchUseCase = getBySearchUseCase,
            getCategoriesUseCase = getCategoriesUseCase,
            getDescriptionUseCase = getDescriptionUseCase,
            getDetailUseCase = getDetailUseCase
        )
        mainViewModel = MainViewModel(
            mercadoLibreUseCases = meliUseCases,
            mercadoLibreRepository = mercadoLibreRepository
        )
    }

    @Test
    fun `get categories response success`() {
        scope.runTest {
            val categoriesResponse = mockk<CategoriesResponse>(relaxed = true)

            coEvery {
                meliUseCases.getCategoriesUseCase()
            } answers { Result.success(categoriesResponse) }

            assert(mainViewModel.listCategories.isEmpty())

            mainViewModel.getCategories()

            coVerify { meliUseCases.getCategoriesUseCase() }

            assert(mainViewModel.listCategories.isNotEmpty())
        }
    }

    @Test
    fun `get categories response failure`() {
        scope.runTest {
            val categoriesResponse = mockk<CategoriesResponse>()

            coEvery {
                meliUseCases.getCategoriesUseCase()
            } answers { Result.failure(Throwable()) }

            mainViewModel.getCategories()
            coVerify { meliUseCases.getCategoriesUseCase() }

            confirmVerified(categoriesResponse)
        }
    }

/*    @After
    fun tearDown() {
        confirmVerified(
            getBySearchUseCase,
            getCategoriesUseCase,
            getDescriptionUseCase,
            getDetailUseCase
        )
    }*/
}
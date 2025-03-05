package com.ingrid.mercadolibre.presentation.detail

import com.ingrid.mercadolibre.data.model.description.DescriptionResponse
import com.ingrid.mercadolibre.data.model.detail.Attribute
import com.ingrid.mercadolibre.data.model.detail.DetailResponse
import com.ingrid.mercadolibre.data.model.detail.Picture
import com.ingrid.mercadolibre.domain.useCases.GetCategoriesUseCase
import com.ingrid.mercadolibre.domain.useCases.GetDescriptionUseCase
import com.ingrid.mercadolibre.domain.useCases.GetDetailUseCase
import com.ingrid.mercadolibre.domain.useCases.GetSearchUseCase
import com.ingrid.mercadolibre.domain.useCases.MercadoLibreUseCases
import com.ingrid.mercadolibre.presentation.screens.detail.DetailViewModel
import com.ingrid.mercadolibre.util.Constants.ITEM_CONDITION
import com.ingrid.mercadolibre.utils.CoroutinesRules
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {
    @get: Rule
    var coroutinesRules = CoroutinesRules()

    private lateinit var detailViewModel: DetailViewModel

    private lateinit var meliUseCases: MercadoLibreUseCases

    private val getBySearchUseCase: GetSearchUseCase = mockk(relaxed = true)
    private val getCategoriesUseCase: GetCategoriesUseCase = mockk(relaxed = true)
    private val getDescriptionUseCase: GetDescriptionUseCase = mockk(relaxed = true)
    private val getDetailUseCase: GetDetailUseCase = mockk(relaxed = true)

    private val scope = TestScope(coroutinesRules.testDispatcher)

    @Before
    fun setUp() {
        meliUseCases = MercadoLibreUseCases(
            getSearchUseCase = getBySearchUseCase,
            getCategoriesUseCase = getCategoriesUseCase,
            getDescriptionUseCase = getDescriptionUseCase,
            getDetailUseCase = getDetailUseCase
        )
        detailViewModel = DetailViewModel(
           mercadoLibreUseCases = meliUseCases
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get details response success`() {
        scope.runTest {
            val detailRespose = mockk<DetailResponse>(relaxed = true)
            val attribute = mockk<Attribute>(relaxed = true)
            val picture = mockk<Picture>(relaxed = true)
            val pictures = listOf(picture)
            val attributes = listOf(attribute)

            every { detailRespose.pictures } returns pictures
            every { picture.url } returns "url"
            every { attribute.id } returns ITEM_CONDITION
            every { detailRespose.attributes } returns attributes

            coEvery { meliUseCases.getDetailUseCase("id") } returns Result.success(detailRespose)

            detailViewModel.getDetails("id")
            advanceUntilIdle()
            verify { detailRespose.pictures }
            verify { detailRespose.attributes }
            verify { attribute.id }
            verify { attribute.value_name }
            verify { picture.url }

            coVerify { meliUseCases.getDetailUseCase("id") }

            confirmVerified(detailRespose, attribute, picture)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get details response failure`() {
        scope.runTest {
            val throwable = mockk<Throwable>()
            val message = "message"

            every { throwable.message } returns message
            coEvery { meliUseCases.getDetailUseCase("id") } returns Result.failure(throwable)

            detailViewModel.getDetails("id")
            advanceUntilIdle()

            coVerify {
                meliUseCases.getDetailUseCase("id")
            }
            verify { throwable.message }

            confirmVerified(throwable)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get description response success`() {
        scope.runTest {
            val description = mockk<DescriptionResponse>(relaxed = true)

            coEvery { meliUseCases.getDescriptionUseCase("id") } returns Result.success(description)

            detailViewModel.getDescription("id")
            advanceUntilIdle()

            coVerify { meliUseCases.getDescriptionUseCase("id") }

            confirmVerified(description)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get description response failure`() {
        scope.runTest {
            val throwable = mockk<Throwable>()
            val message = "message"

            every { throwable.message } returns message
            coEvery { meliUseCases.getDescriptionUseCase("id") } returns Result.failure(throwable)

            detailViewModel.getDescription("id")
            advanceUntilIdle()

            coVerify {
                meliUseCases.getDescriptionUseCase("id")
            }
            verify { throwable.message }

            confirmVerified(throwable)
        }
    }

    @Test
    fun `get quantity returns correct value based on sold_quantity`() {
        val detailResponse0 = DetailResponse(sold_quantity = 10)
        val detailResponse1 = DetailResponse(sold_quantity = 30)
        val detailResponse2 = DetailResponse(sold_quantity = 50)
        val detailResponse3 = DetailResponse(sold_quantity = 70)
        val detailResponse4 = DetailResponse(sold_quantity = 90)

        assertEquals(0, detailViewModel.getQuantity(detailResponse0))
        assertEquals(1, detailViewModel.getQuantity(detailResponse1))
        assertEquals(2, detailViewModel.getQuantity(detailResponse2))
        assertEquals(3, detailViewModel.getQuantity(detailResponse3))
        assertEquals(4, detailViewModel.getQuantity(detailResponse4))
    }

    @After
    fun tearDown() {
        confirmVerified(
            getBySearchUseCase,
            getCategoriesUseCase,
            getDescriptionUseCase,
            getDetailUseCase
        )
    }
}
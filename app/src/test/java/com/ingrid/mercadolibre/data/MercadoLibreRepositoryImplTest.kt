package com.ingrid.mercadolibre.data

import com.google.common.truth.Truth.assertThat
import com.ingrid.mercadolibre.data.model.search.SearchResponse
import com.ingrid.mercadolibre.data.remote.MercadoLibreApi
import com.ingrid.mercadolibre.data.repositoryImpl.MercadoLibreRepositoryImpl
import com.ingrid.mercadolibre.json.malformedGeDescription
import com.ingrid.mercadolibre.json.malformedGetBySearch
import com.ingrid.mercadolibre.json.malformedGetCategories
import com.ingrid.mercadolibre.json.malformedGetDetails
import com.ingrid.mercadolibre.util.Util.readJsonFile
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import org.junit.After
import org.junit.Before
import org.junit.Test
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MercadoLibreRepositoryImplTest {

    private lateinit var mercadoLibreRepositoryImpl: MercadoLibreRepositoryImpl
    private lateinit var meliApi: MercadoLibreApi
    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        okHttpClient = OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .build()
        meliApi = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(MercadoLibreApi::class.java)

        mercadoLibreRepositoryImpl = MercadoLibreRepositoryImpl(api = meliApi)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getBySearch, valid response, returns success`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(readJsonFile("response_get_by_search.json"))
        )
        val result = mercadoLibreRepositoryImpl.getBySearch(
            product = "carro",
            limit = 0,
            offset = 50
        )
        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `getBySearch, malformed response, returns failure`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(malformedGetBySearch)
        )
        val result = mercadoLibreRepositoryImpl.getBySearch(
            product = "carro",
            limit = 0,
            offset = 50
        )
        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `getDetails, valid response, returns success`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(readJsonFile("response_get_details.json"))
        )
        val result = mercadoLibreRepositoryImpl.getDetails(id = "MCO578617981")
        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `getDetails, invalid response, returns failure`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(400)
                .setBody(readJsonFile("response_get_details.json"))
        )
        val result = mercadoLibreRepositoryImpl.getDetails(id = "MCO578617981")
        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `getDetails, malformed response, returns failure`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(malformedGetDetails)
        )
        val result = mercadoLibreRepositoryImpl.getDetails(id = "MCO578617981")
        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `getDescription, valid response, returns success`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(readJsonFile("response_get_description.json"))
        )
        val result = mercadoLibreRepositoryImpl.getDescription(id = "MCO578617981")
        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `getDescription, invalid response, returns failure`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(400)
                .setBody(readJsonFile("response_get_description.json"))
        )
        val result = mercadoLibreRepositoryImpl.getDescription(id = "MCO578617981")
        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `getDescription, malformed response, returns failure`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(malformedGeDescription)
        )
        val result = mercadoLibreRepositoryImpl.getDescription(id = "MCO578617981")
        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `getCategories, valid response, returns success`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(readJsonFile("response_get_categories.json"))
        )
        val result = mercadoLibreRepositoryImpl.getCategories()
        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `getCategories, invalid response, returns failure`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(400)
                .setBody(readJsonFile("response_get_categories.json"))
        )
        val result = mercadoLibreRepositoryImpl.getCategories()
        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `getCategories, malformed response, returns failure`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(malformedGetCategories)
        )
        val result = mercadoLibreRepositoryImpl.getCategories()
        assertThat(result.isFailure).isTrue()
    }
}
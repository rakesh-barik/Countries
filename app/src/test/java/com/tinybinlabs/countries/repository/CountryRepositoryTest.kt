package com.tinybinlabs.countries.repository

import com.google.gson.GsonBuilder
import com.tinybinlabs.countries.cache.CountryDaoFake
import com.tinybinlabs.countries.cache.CountryDatabaseFake
import com.tinybinlabs.countries.cache.model.CountryDbEntity
import com.tinybinlabs.countries.cache.util.CountryDbMapper
import com.tinybinlabs.countries.network.CountryService
import com.tinybinlabs.countries.network.response.MockWebServerResponse
import com.tinybinlabs.countries.network.util.CountryNetworkMapper
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection


class CountryRepositoryTest {
    private val appDatabase = CountryDatabaseFake()
    private lateinit var mockWebServer: MockWebServer
    private lateinit var baseUrl: HttpUrl

    private lateinit var countryService: CountryService
    private lateinit var countryDao: CountryDaoFake
    private val mapper = CountryNetworkMapper()
    private val dbMapper = CountryDbMapper()
    private val token = "123456789"


    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        baseUrl = mockWebServer.url("/api/all/")
        countryService = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(CountryService::class.java)
        countryDao = CountryDaoFake(appDatabase)
    }

    @Test
    fun getCountriesFromNetwork_saveToDb(): Unit = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(MockWebServerResponse.countryListResponse)
        )

        val result = countryService.getAllCountries(token)
        for (country in result) {
            countryDao.insertCountry(mapper.mapToDbEntity(country))
        }
        assert(countryDao.getCountries().isNotEmpty())
    }

    @Test
    fun getCountryById(): Unit = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(MockWebServerResponse.countryListResponse)
        )

        val result = countryService.getAllCountries(token)
        for (country in result) {
            country.id = 1
            countryDao.insertCountry(mapper.mapToDbEntity(country))
        }
        val co: CountryDbEntity? = countryDao.getCountryById(1)
        co?.name?.isNotEmpty()?.let { assert(it) }
    }

    @Test
    fun searchByName(): Unit = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(MockWebServerResponse.countryListResponse)
        )

        val result = countryService.getAllCountries(token)
        for (country in result) {
            countryDao.insertCountry(mapper.mapToDbEntity(country))
        }
        val list = countryDao.getCountryByName("Afgha")
        assert(list.isNotEmpty())
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }
}
package com.tinybinlabs.countries.network

import com.tinybinlabs.countries.network.response.AllCountriesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CountryService {

    @GET("all")
    suspend fun getAllCountries(@Query("access_key") accessKey: String): AllCountriesResponse
}
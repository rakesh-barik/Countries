package com.tinybinlabs.countries.repository

import android.util.Log
import com.tinybinlabs.countries.domain.Country
import com.tinybinlabs.countries.network.CountryService
import com.tinybinlabs.countries.network.util.CountryNetworkMapper

class CountryRepositoryImpl(
    private val countryService: CountryService,
    private val mapper: CountryNetworkMapper
): CountryRepository {

    override suspend fun getCountries(token: String): List<Country> {
        Log.d("Countries", "getCountries called in repository.")
        val result = countryService.getAllCountries(token)
        return mapper.fromEntityList(result)
    }

}
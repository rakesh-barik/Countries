package com.tinybinlabs.countries.repository

import com.tinybinlabs.countries.domain.Country

interface CountryRepository {

    suspend fun getCountries(token: String): List<Country>

}
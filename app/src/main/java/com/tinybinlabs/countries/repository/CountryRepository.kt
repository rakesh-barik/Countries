package com.tinybinlabs.countries.repository

import com.tinybinlabs.countries.domain.Country
import kotlinx.coroutines.flow.Flow

interface CountryRepository {

    suspend fun getCountries(token: String?): Flow<List<Country>>

    suspend fun getCountryById(id : Int): Country?

}
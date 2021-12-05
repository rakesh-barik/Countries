package com.tinybinlabs.countries.repository

import com.tinybinlabs.countries.domain.Country
import kotlinx.coroutines.flow.Flow

interface CountryRepository {

    suspend fun getCountries(isNetAvailable: Boolean): Flow<List<Country>>

    suspend fun getCountryById(id : Int): Country?

    suspend fun searchCountry(name:String, isNetAvailable: Boolean): Flow<List<Country>>

    suspend fun toggleFavorite(id: Int, isFav: Boolean)

    suspend fun getFavoriteCountries(): Flow<List<Country>>

}
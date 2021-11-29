package com.tinybinlabs.countries.repository

import android.util.Log
import com.tinybinlabs.countries.cache.CountryDao
import com.tinybinlabs.countries.cache.util.CountryDbMapper
import com.tinybinlabs.countries.domain.Country
import com.tinybinlabs.countries.network.CountryService
import com.tinybinlabs.countries.network.util.CountryNetworkMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CountryRepositoryImpl(
    private val countryService: CountryService,
    private val mapper: CountryNetworkMapper,
    private val dao: CountryDao,
    private val dbMapper: CountryDbMapper
): CountryRepository {

    override suspend fun getCountries(token: String?): Flow<List<Country>> = flow {
        Log.d("Countries", "getCountries called in repository. token : $token")
        try {
            token?.let {
                val result = countryService.getAllCountries(token)
                for (country in result) {
                    dao.insertCountry(mapper.mapToDbEntity(country))
                }
            }
            Log.d("Countries", "getCountries from Db")
            val cachedCountries = dao.getCountries()
            emit(dbMapper.fromEntityList(cachedCountries))
        } catch (e: Exception) {
            emit(emptyList<Country>())
        }

    }

    override suspend fun getCountryById(id: Int): Country? {
        return dao.getCountryById(id)?.let { dbMapper.mapFromEntity(it) }
    }
}
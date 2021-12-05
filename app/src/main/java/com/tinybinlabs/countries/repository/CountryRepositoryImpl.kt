package com.tinybinlabs.countries.repository

import android.util.Log
import com.tinybinlabs.countries.cache.CountryDao
import com.tinybinlabs.countries.cache.util.CountryDbMapper
import com.tinybinlabs.countries.domain.Country
import com.tinybinlabs.countries.network.CountryService
import com.tinybinlabs.countries.network.util.CountryNetworkMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Named

class CountryRepositoryImpl(
    private val countryService: CountryService,
    private val mapper: CountryNetworkMapper,
    private val dao: CountryDao,
    private val dbMapper: CountryDbMapper,
    @Named("access_key") private val token: String
) : CountryRepository {

    override suspend fun getCountries(
        isNetAvailable: Boolean
    ): Flow<List<Country>> =
        flow {
            Log.d("Countries", "getCountries in repository. token : $token, net: $isNetAvailable")
            try {
                val cachedCountries = dao.getCountries()
                if (!cachedCountries.isNullOrEmpty()) {
                    emit(dbMapper.fromEntityList(cachedCountries))
                } else {
                    if (isNetAvailable) {
                        //fetch from server
                        val result = countryService.getAllCountries(token)
                        for (country in result) {
                            dao.insertCountry(mapper.mapToDbEntity(country))
                        }
                    }
                    val countryList = dao.getCountries()
                    if (!countryList.isNullOrEmpty()) {
                        emit(dbMapper.fromEntityList(cachedCountries))
                    } else {
                        emit(emptyList<Country>())
                    }
                }

            } catch (e: Exception) {
                Log.e("CountryRepo", "${e.message}")
                emit(emptyList<Country>())
            }
        }

    override suspend fun getCountryById(id: Int): Country? {
        return dao.getCountryById(id)?.let { dbMapper.mapFromEntity(it) }
    }


    override suspend fun searchCountry(
        name: String,
        isNetAvailable: Boolean
    ): Flow<List<Country>> =
        flow {
            try {
                val cachedCountries = dao.getCountryByName(name)
                if (!cachedCountries.isNullOrEmpty()) {
                    emit(dbMapper.fromEntityList(cachedCountries))
                } else {
                    if (isNetAvailable) {
                        //fetch from server
                        val result = countryService.searchCountries(name, token)
                        for (country in result) {
                            dao.insertCountry(mapper.mapToDbEntity(country))
                        }
                    }
                    val countryList = dao.getCountryByName(name)
                    if (!countryList.isNullOrEmpty()) {
                        emit(dbMapper.fromEntityList(cachedCountries))
                    } else {
                        emit(emptyList<Country>())
                    }
                }

            } catch (e: Exception) {
                Log.e("CountryRepo", "${e.message}")
                emit(emptyList<Country>())
            }
        }

    override suspend fun toggleFavorite(id: Int, isFav: Boolean) {
        val fav = if (isFav) 1 else 0
        dao.updateFavorite(fav, id = id)
    }

    override suspend fun getFavoriteCountries(): Flow<List<Country>> = flow {
        try {
            val favCountries = dao.getFavoriteCountries()
            if (favCountries.isNullOrEmpty()) {
                emit(emptyList<Country>())
            } else {
                emit(dbMapper.fromEntityList(favCountries))
            }
        }catch (e: Exception) {
            emit(emptyList<Country>())
            Log.e("CountryRepo", "${e.message}")
        }
    }
}
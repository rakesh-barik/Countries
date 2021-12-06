package com.tinybinlabs.countries.cache

import com.tinybinlabs.countries.cache.model.CountryDbEntity

class CountryDaoFake(
    private val appDatabaseFake: CountryDatabaseFake
): CountryDao{
    override suspend fun getCountries(): List<CountryDbEntity> {
        return appDatabaseFake.countries
    }

    override suspend fun insertCountry(dbEntity: CountryDbEntity): Long {
        appDatabaseFake.countries.add(dbEntity)
        return 1
    }

    override suspend fun getCountryById(id: Int): CountryDbEntity? {
        return appDatabaseFake.countries.find { it.id == id }
    }

    override suspend fun getCountryByName(name: String): List<CountryDbEntity> {
        return appDatabaseFake.countries //just for testing
    }

    override suspend fun updateFavorite(isFav: Int, id: Int) {
        appDatabaseFake.countries.find { it.id == id }
    }

    override suspend fun getFavoriteCountries(): List<CountryDbEntity> {
        return appDatabaseFake.countries
    }
}
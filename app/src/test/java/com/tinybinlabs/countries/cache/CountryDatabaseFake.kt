package com.tinybinlabs.countries.cache

import com.tinybinlabs.countries.cache.model.CountryDbEntity

class CountryDatabaseFake {
    val countries = mutableListOf<CountryDbEntity>()
}
package com.tinybinlabs.countries.cache.util

import com.tinybinlabs.countries.cache.model.CountryDbEntity
import com.tinybinlabs.countries.domain.Country
import com.tinybinlabs.countries.domain.util.EntityMapper

class CountryDbMapper : EntityMapper<CountryDbEntity, Country> {
    override fun mapFromEntity(entity: CountryDbEntity): Country {
        return Country(
            entity.id,
            entity.name,
            entity.capital,
            entity.region,
            entity.alpha2Code,
            entity.alpha3Code,
            entity.altSpellings ?: listOf(),
            entity.callingCodes ?: listOf(),
            entity.topLevelDomain ?: listOf(),
            isFav = entity.isFav > 0
        )
    }

    fun fromEntityList(dbEntityList: List<CountryDbEntity>): List<Country> {
        return dbEntityList.map {
            mapFromEntity(it)
        }
    }

}
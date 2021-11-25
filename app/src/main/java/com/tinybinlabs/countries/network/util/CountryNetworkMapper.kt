package com.tinybinlabs.countries.network.util

import com.tinybinlabs.countries.domain.Country
import com.tinybinlabs.countries.domain.util.EntityMapper
import com.tinybinlabs.countries.network.model.CountryNetworkEntity

/**
 * An implementation of Entity Mapper.
 * Maps the network entity and domain model.
 * */
class CountryNetworkMapper : EntityMapper<CountryNetworkEntity, Country> {

    override fun mapFromEntity(entity: CountryNetworkEntity): Country {
        return Country(
            entity.id,
            entity.name,
            entity.capital,
            entity.region,
            entity.alpha2Code,
            entity.alpha3Code,
            entity.altSpellings ?: listOf(),
            entity.callingCodes ?: listOf(),
            entity.topLevelDomain ?: listOf()
        )
    }

    fun fromEntityList(countryNetworkEntityList: List<CountryNetworkEntity>): List<Country> {
        return countryNetworkEntityList.map {
            mapFromEntity(it)
        }
    }
}
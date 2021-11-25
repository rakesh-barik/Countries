package com.tinybinlabs.countries.domain.util

/**
 * A mapper which sets a contract between domain and data layer.
 * Helps to map the fetched data and required data for higher layer.
 * */
interface EntityMapper<Entity, DomainModel> {
    fun mapFromEntity(entity: Entity): DomainModel
}
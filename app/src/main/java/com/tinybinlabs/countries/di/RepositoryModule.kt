package com.tinybinlabs.countries.di

import com.tinybinlabs.countries.cache.CountryDao
import com.tinybinlabs.countries.cache.util.CountryDbMapper
import com.tinybinlabs.countries.network.CountryService
import com.tinybinlabs.countries.network.util.CountryNetworkMapper
import com.tinybinlabs.countries.repository.CountryRepository
import com.tinybinlabs.countries.repository.CountryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        countryService: CountryService,
        mapper: CountryNetworkMapper,
        countryDao: CountryDao,
        dbMapper: CountryDbMapper
    ): CountryRepository {
        return CountryRepositoryImpl(countryService, mapper, countryDao, dbMapper)
    }
}
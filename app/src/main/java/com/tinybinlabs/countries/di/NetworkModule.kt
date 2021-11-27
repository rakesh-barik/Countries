package com.tinybinlabs.countries.di

import com.google.gson.GsonBuilder
import com.tinybinlabs.countries.network.CountryService
import com.tinybinlabs.countries.network.util.CountryNetworkMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideCountryMapper(): CountryNetworkMapper {
        return CountryNetworkMapper()
    }

    @Singleton
    @Provides
    fun provideCountryService(): CountryService {
        return Retrofit.Builder()
            .baseUrl("http://api.countrylayer.com/v2/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(CountryService::class.java)
    }

    /**
     * In order to keep the repo ready to run keeping the
     * access token here, ideally it should be kept in
     * local.properties file and accessed through
     * BuildConfig.java class as a public static final ACCESS_TOKEN
     *
     * example : BuildConfig.ACCESS_TOKEN
     * */
    @Singleton
    @Provides
    @Named("access_key")
    fun provideAccessToken(): String {
        return "3a8117c25921493e4717a68346c00375"
    }
}
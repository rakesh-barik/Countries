package com.tinybinlabs.countries.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.tinybinlabs.countries.CountriesApplication
import com.tinybinlabs.countries.cache.CountryDao
import com.tinybinlabs.countries.cache.CountryDatabase
import com.tinybinlabs.countries.cache.util.CountryDbMapper
import com.tinybinlabs.countries.presentation.util.InternetConManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): CountriesApplication {
        return app as CountriesApplication
    }

    @Singleton
    @Provides
    fun provideInternetConManager(@ApplicationContext app: Context): InternetConManager {
        return InternetConManager(app = app as CountriesApplication)
    }

    @Provides
    @Singleton
    fun provideCountryDatabase(app: Application): CountryDatabase {
        return Room.databaseBuilder(
            app,
            CountryDatabase::class.java,
            CountryDatabase.DATABASE_NAME
        ).addMigrations(CountryDatabase.MIGRATION_1_2).build()
    }

    @Singleton
    @Provides
    fun provideDbMapper(): CountryDbMapper {
        return CountryDbMapper()
    }

    @Singleton
    @Provides
    fun provideCountryDao(db: CountryDatabase): CountryDao {
        return db.countryDao
    }
}
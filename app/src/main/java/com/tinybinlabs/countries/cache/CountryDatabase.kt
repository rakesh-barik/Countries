package com.tinybinlabs.countries.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tinybinlabs.countries.cache.model.CountryDbEntity

@Database(
    entities = [CountryDbEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class CountryDatabase : RoomDatabase() {
    abstract val countryDao : CountryDao

    companion object {
        const val DATABASE_NAME = "countries_db"
    }
}
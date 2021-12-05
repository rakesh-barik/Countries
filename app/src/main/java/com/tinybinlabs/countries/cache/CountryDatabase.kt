package com.tinybinlabs.countries.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tinybinlabs.countries.cache.model.CountryDbEntity


@Database(
    entities = [CountryDbEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class CountryDatabase : RoomDatabase() {
    abstract val countryDao : CountryDao

    companion object {
        const val DATABASE_NAME = "countries_db"
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE country "
                            + " ADD COLUMN isFav INTEGER DEFAULT 0 NOT NULL CHECK(isFav IN (0,1)"
                )
            }
        }
    }


}
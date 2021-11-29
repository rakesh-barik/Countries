package com.tinybinlabs.countries.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tinybinlabs.countries.cache.model.CountryDbEntity

@Dao
interface CountryDao {

    @Query("SELECT * FROM country")
    suspend fun getCountries(): List<CountryDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(dbEntity: CountryDbEntity): Long

    @Query("SELECT * FROM country where id = :id")
    suspend fun getCountryById(id: Int): CountryDbEntity?

}
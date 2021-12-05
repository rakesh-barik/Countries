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

    @Query("SELECT * FROM country where name LIKE '%' ||:name|| '%' or altSpellings LIKE '%' ||:name|| '%'")
    suspend fun getCountryByName(name: String): List<CountryDbEntity>

    @Query("UPDATE country SET isFav = :isFav where id = :id")
    suspend fun updateFavorite(isFav: Int, id: Int)

    @Query("SELECT * FROM country where isFav = 1")
    suspend fun getFavoriteCountries(): List<CountryDbEntity>

}
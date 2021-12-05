package com.tinybinlabs.countries.cache.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "country", indices = [Index(value = ["name"], unique = true)])
class CountryDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    val name: String?,
    val capital: String?,
    val region: String?,
    val alpha2Code: String?,
    val alpha3Code: String?,
    val altSpellings: List<String>,
    val callingCodes: List<String>,
    val topLevelDomain: List<String>,
    val isFav: Int = 0
)
package com.tinybinlabs.countries.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val id:Int,
    val name:String,
    val capital: String,
    val region: String,
    val alpha2Code: String,
    val alpha3Code: String,
    val altSpellings: List<String>,
    val callingCodes: List<String>,
    val topLevelDomain: List<String>
) : Parcelable

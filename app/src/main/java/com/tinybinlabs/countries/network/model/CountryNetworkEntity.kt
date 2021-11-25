package com.tinybinlabs.countries.network.model

import com.google.gson.annotations.SerializedName

/**
 * Entity class for the network response.
 * The variables are nullable here as we might receive any of these
 * values as NULL.
 * */
class CountryNetworkEntity(

    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("capital")
    var capital: String? = null,
    @SerializedName("region")
    var region: String? = null,
    @SerializedName("alpha2Code")
    var alpha2Code: String? = null,
    @SerializedName("alpha3Code")
    var alpha3Code: String? = null,
    @SerializedName("altSpellings")
    var altSpellings: List<String>? = null,
    @SerializedName("callingCodes")
    var callingCodes: List<String>? = null,
    @SerializedName("topLevelDomain")
    var topLevelDomain: List<String>? = null

)
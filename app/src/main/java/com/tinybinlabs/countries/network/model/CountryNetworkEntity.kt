package com.tinybinlabs.countries.network.model

import com.google.gson.annotations.SerializedName

class CountryNetworkEntity (

    @SerializedName("name")
    var name:String,
    @SerializedName("capital")
    var capital: String,
    @SerializedName("region")
    var region: String,
    @SerializedName("alpha2Code")
    var alpha2Code: String,
    @SerializedName("alpha3Code")
    var alpha3Code: String,
    @SerializedName("altSpellings")
    var altSpellings: List<String>,
    @SerializedName("callingCodes")
    var callingCodes: List<String>,
    @SerializedName("topLevelDomain")
    var topLevelDomain: List<String>

)
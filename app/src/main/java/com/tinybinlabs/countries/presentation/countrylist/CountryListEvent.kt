package com.tinybinlabs.countries.presentation.countrylist

sealed class CountryListEvent {
    object RefreshListEvent : CountryListEvent()
    data class SearchEvent(val name: String) : CountryListEvent()
}
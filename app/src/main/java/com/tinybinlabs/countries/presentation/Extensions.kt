package com.tinybinlabs.countries.presentation

import java.util.*

fun <T> T.toFlagEmoji(): String where T:Alpha2, T: Flag {

    val alpha2: String = this.alpha2Code

    // 1. It first checks if the string consists of only 2 characters: ISO 3166-1 alpha-2 two-letter country codes (https://en.wikipedia.org/wiki/Regional_Indicator_Symbol).
    if (alpha2.length != 2) {
        return alpha2
    }

    val countryCodeCaps =
        alpha2.uppercase(Locale.getDefault()) // upper case is important because we are calculating offset
    val firstLetter = Character.codePointAt(countryCodeCaps, 0) - 0x41 + 0x1F1E6
    val secondLetter = Character.codePointAt(countryCodeCaps, 1) - 0x41 + 0x1F1E6

    // 2. It then checks if both characters are alphabet
    if (!countryCodeCaps[0].isLetter() || !countryCodeCaps[1].isLetter()) {
        return alpha2
    }

    return String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
}

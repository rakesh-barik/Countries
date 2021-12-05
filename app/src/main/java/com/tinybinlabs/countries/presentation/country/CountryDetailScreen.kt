package com.tinybinlabs.countries.presentation.country

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tinybinlabs.countries.domain.Country
import com.tinybinlabs.countries.presentation.Flag
import com.tinybinlabs.countries.presentation.components.DetailAppBar
import com.tinybinlabs.countries.presentation.toFlagEmoji

@Composable
fun CountryDetailScreen(
    navController: NavController,
    viewModel: CountryDetailViewModel = hiltViewModel()
) {
    val country: Country? = viewModel.state.value.country
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            DetailAppBar(
                isFav = country?.isFav ?: false,
                onNavigateUp = {
                    navController.navigateUp()
                },
                onTriggerFavorite = { viewModel.toggleFavorite(it)}
            )
        },

        ) {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .padding(24.dp),
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .verticalScroll(rememberScrollState()),

                ) {
                DetailRow(
                    image = Icons.Default.Language,
                    string = country?.name,
                    contentDesc = "Country name"
                )
                DetailRow(
                    image = Icons.Default.Domain,
                    string = country?.topLevelDomain?.joinToString(separator = ", "),
                    contentDesc = "domain"
                )
                DetailRow(
                    image = Icons.Default.Flag,
                    string = country?.alpha2Code?.let { it1 -> Flag(it1).toFlagEmoji() },
                    contentDesc = "flag"
                )
                DetailRow(
                    image = Icons.Default.LocalAirport,
                    string = country?.alpha3Code,
                    contentDesc = "airport"
                )
                DetailRow(
                    image = Icons.Default.Call,
                    string = country?.callingCodes?.joinToString(separator = ", "),
                    contentDesc = "country code"
                )
                DetailRow(
                    image = Icons.Default.LocationCity,
                    string = country?.capital,
                    contentDesc = "location"
                )
                DetailRow(
                    image = Icons.Default.LocationOn,
                    string = country?.region,
                    contentDesc = "region"
                )
                DetailRow(
                    image = Icons.Default.ListAlt,
                    string = country?.altSpellings?.joinToString(separator = ", "),
                    contentDesc = "alt spellings"
                )
            }
        }
    }
}

@Composable
private fun DetailRow(image: ImageVector, string: String?, contentDesc: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Icon(imageVector = image, contentDesc)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = string ?: "",
            modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally)
        )
    }
}
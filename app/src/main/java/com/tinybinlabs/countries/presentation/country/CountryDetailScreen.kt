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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tinybinlabs.countries.domain.Country
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
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(align = Alignment.Start)
                    ) {
                        Text(text = "Detail")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                    }
                }
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.Language, contentDescription = "location")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = country?.name ?: "",
                        modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.Domain, contentDescription = "location")
                    Spacer(modifier = Modifier.width(8.dp))
                    country?.topLevelDomain?.joinToString(separator = ", ")?.let { it1 ->
                        Text(
                            text = it1,
                            modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.Flag, contentDescription = "location")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = country?.alpha2Code?.toFlagEmoji() ?: "",
                        modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.LocalAirport, contentDescription = "location")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = country?.alpha3Code ?: "",
                        modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.Call, contentDescription = "location")
                    Spacer(modifier = Modifier.width(8.dp))
                    country?.callingCodes?.joinToString(separator = ", ")?.let { it1 ->
                        Text(
                            text = it1,
                            modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.LocationCity, contentDescription = "location")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = country?.capital ?: "",
                        modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = "location")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = country?.region ?: "",
                        modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.ListAlt, contentDescription = "location")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = country?.altSpellings?.joinToString(separator = ", ") ?: "",
                        modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }

            }
        }

    }
}
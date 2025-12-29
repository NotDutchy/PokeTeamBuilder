package com.example.poketeambuilder.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.poketeambuilder.R
import com.example.poketeambuilder.ui.cards.PokeCard
import com.example.poketeambuilder.ui.screens.destinations.DetailedPokeCardDestination
import com.example.poketeambuilder.ui.viewmodels.PokeDexViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
@RootNavGraph(start = true)
fun PokeDexScreen(
    viewmodel: PokeDexViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {

    LaunchedEffect(true) {
        viewmodel.loadPokes()
    }

    val coScope = rememberCoroutineScope()
    val currentPokeList = viewmodel.currentPokeList.observeAsState()
    val searchFilter = remember { mutableStateOf("") }

    suspend fun applyFilter() {
        val filterString = if (searchFilter.value != "") searchFilter.value else null

        viewmodel.getFilteredPoke(
            filterString,
            null,
            null
        )
    }

    Scaffold(
        backgroundColor = colorResource(id = R.color.color_background),
        topBar = {
            TopAppBar(title = {
                Text(text = "Pokedex")
            }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(226, 68, 98)))
        }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    shape = RoundedCornerShape(16.dp),
                    label = { Text(text = "Search on name or number")},
                    singleLine = true,
                    leadingIcon = { Icon(Icons.Default.Search, "Search")},
                    value = searchFilter.value,
                    onValueChange = { newValue ->
                    searchFilter.value = newValue

                    coScope.launch {
                        applyFilter()
                    }
                })
            }
            Column(modifier = Modifier.padding(it)) {
                currentPokeList.value?.let { pokeList ->
                    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                        items(pokeList) { poke ->
                            PokeCard(poke = poke) {
                                navigator.navigate(DetailedPokeCardDestination(poke))
                            }
                        }
                    }
                }

            }
        }

    }
}
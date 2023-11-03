package com.example.poketeambuilder.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.poketeambuilder.domain.Pokemon
import com.example.poketeambuilder.ui.cards.PokeCard
import com.example.poketeambuilder.ui.screens.destinations.DetailedPokeCardDestination
import com.example.poketeambuilder.ui.viewmodels.PokeDexViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
@RootNavGraph(start = true)
fun PokeDexScreen (
    viewmodel: PokeDexViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {

    LaunchedEffect(true) {
        viewmodel.loadPokes()
    }

    val currentPokeList = viewmodel.currentPokeList.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Pokedex")
            }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(226, 68,98)))
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            currentPokeList.value?.let { pokeList ->
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    items(pokeList) {poke ->
                        PokeCard(poke = poke) {
                            navigator.navigate(DetailedPokeCardDestination(poke))
                        }
                    }
                }
            }

        }
    }
}
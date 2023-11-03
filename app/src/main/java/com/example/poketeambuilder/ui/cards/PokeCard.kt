package com.example.poketeambuilder.ui.cards

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.poketeambuilder.R
import com.example.poketeambuilder.domain.Pokemon
import com.example.poketeambuilder.domain.TypeColorHelper
import com.example.poketeambuilder.ui.screens.destinations.DetailedPokeCardDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.qualifiers.ApplicationContext

@Composable
fun PokeCard(
    poke: Pokemon,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        backgroundColor = colorResource(id = TypeColorHelper.determineTypeColor(poke.types[0])),
        shape = RoundedCornerShape(
            16.dp
        ),
        modifier = modifier.fillMaxSize().clickable {
            onClick.invoke()
        }

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = poke.imageUrl,
                contentDescription = "Pokemon Image"
            )
            Text(text = poke.name)
            Text(text = "#${poke.id}")
        }
    }
}
package com.example.poketeambuilder.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.poketeambuilder.domain.Move
import com.example.poketeambuilder.domain.MoveStats
import com.example.poketeambuilder.domain.Pokemon
import com.example.poketeambuilder.domain.TypeColorHelper
import com.example.poketeambuilder.ui.viewmodels.DetailedPokeCardViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun DetailedPokeCard(
    viewModel: DetailedPokeCardViewModel = hiltViewModel(),
    poke: Pokemon,
    navigator: NavController
) {

/*    LaunchedEffect(true) {
        viewModel.currentPoke.
    }*/

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            backgroundColor = colorResource(id = TypeColorHelper.determineTypeColor(type = poke.types[0])),
            title = {
                Text(text = poke.name)
            },
            elevation = 0.dp,
            navigationIcon = {
                if (navigator.previousBackStackEntry != null) {
                    IconButton(onClick =
                    {
                        navigator.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            })
        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = colorResource(id = TypeColorHelper.determineTypeColor(type = poke.types[0])),
            elevation = 0.dp,
            shape = RoundedCornerShape(0.dp, 0.dp, 32.dp, 32.dp)
        ) {
            AsyncImage(
                model = poke.imageUrl, contentDescription = "Pokemon Image", modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = poke.name)

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                for (type in poke.types) {
                    TypeContainer(type = type)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "${poke.weight / 10} KG"
                    )
                    Text(
                        text = "Weight"
                    )
                }

                Column {
                    Text(
                        text = "${poke.height / 10} M"
                    )
                    Text(
                        text = "Height"
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Stats")

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                StatRow(statName = "HP", value = poke.hpStat)
                StatRow(statName = "ATK", value = poke.attStat)
                StatRow(statName = "DEF", value = poke.defStat)
                StatRow(statName = "SP.ATK", value = poke.spAttStat)
                StatRow(statName = "SP.DEF", value = poke.spDefStat)
                StatRow(statName = "SPD", value = poke.spdStat)
            }
        }
    }

}

@Composable
fun TypeContainer(type: String) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = colorResource(id = TypeColorHelper.determineTypeColor(type = type)),
        modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.4).dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = type, modifier = Modifier.padding(4.dp))
        }
    }
}

@Composable
fun StatRow(statName: String, value: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = statName,
            modifier = Modifier.fillMaxWidth(0.2f)
        )
        LinearProgressIndicator(
            progress = (value / 255f),
            color = colorResource(id = TypeColorHelper.determineStatColor(statName)),
            backgroundColor = Color.Black,
            modifier = Modifier.fillMaxWidth(0.7f),
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = "$value", modifier = Modifier.fillMaxWidth(0.4f))
    }
}

@Composable
fun MoveRow() {

}
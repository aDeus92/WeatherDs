package com.example.weatherds.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherds.R
import com.example.weatherds.data.WeatherModule
import com.example.weatherds.ui.theme.BlueLight

@Composable

fun MainCard(
    weatherModule: MutableState<WeatherModule>,
    onClickSync: () -> Unit,
    onClickSearch: () -> Unit
) {
    Column(
        modifier = Modifier.padding(5.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = BlueLight)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(

                        text = weatherModule.value.timeLast,
                        style = TextStyle(fontSize = 15.sp),
                        color = Color.White,
                        modifier = Modifier.padding(
                            start = 8.dp, top = 8.dp
                        )

                    )
                    AsyncImage(
                        model = "https:${weatherModule.value.icon}",
                        contentDescription = "img",
                        modifier = Modifier
                            .padding(
                                start = 8.dp, top = 8.dp, end = 8.dp
                            )
                            .size(35.dp)
                    )
                }
                Text(
                    text = translate( weatherModule.value.city),
                    style = TextStyle(fontSize = 25.sp),
                    color = Color.White
                )
                Text(
                    text =
                    weatherModule.value.currentTemp.ifEmpty { "${weatherModule.value.maxTemp}/${weatherModule.value.minTemp}" },

                    style =
                    if (weatherModule.value.currentTemp.isEmpty())
                        TextStyle(fontSize = 50.sp) else
                        TextStyle(fontSize = 100.sp),
                    color = Color.White
                )

                Text(
                    text = translate( weatherModule.value.condition),
                    style = TextStyle(fontSize = 15.sp),
                    color = Color.White
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = {
                        onClickSearch.invoke()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.search_24),
                            contentDescription = "img",
                            tint = Color.White
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = 20.dp),
                        text = /*weatherModule.value.currentTemp.ifEmpty {*/
                        weatherModule.value.maxTemp + "/" + weatherModule.value.minTemp,
                        style = TextStyle(fontSize = 10.sp),
                        color = Color.White
                    )
                    IconButton(onClick = {
                        onClickSync.invoke()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.refresh_24),
                            contentDescription = "img",
                            tint = Color.White
                        )
                    }
                }

            }
        }
    }
}



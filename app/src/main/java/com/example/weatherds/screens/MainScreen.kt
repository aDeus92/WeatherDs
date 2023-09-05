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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherds.R
import com.example.weatherds.ui.theme.BlueLight

@Preview(showBackground = true)
@Composable

fun MainCard() {

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

                        text = "04 Сент 2023 17:12",
                        style = TextStyle(fontSize = 15.sp),
                        color = Color.White,
                        modifier = Modifier.padding(
                            start = 8.dp, top = 8.dp
                        )

                    )
                    AsyncImage(
                        model = "https://cdn.weatherapi.com/weather/64x64/day/116.png",
                        contentDescription = "img",
                        modifier = Modifier
                            .padding(
                                start = 8.dp, top = 8.dp, end = 8.dp
                            )
                            .size(35.dp)
                    )
                }
                Text(
                    text = "Ставрополь", style = TextStyle(fontSize = 25.sp), color = Color.White
                )
                Text(
                    text = "26°C", style = TextStyle(fontSize = 100.sp), color = Color.White
                )

                Text(
                    text = "Воняет", style = TextStyle(fontSize = 15.sp), color = Color.White
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = {
                        /*TODO*/
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.search_24),
                            contentDescription = "img",
                            tint = Color.White
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = 20.dp),
                        text = "20°C/28°C",
                        style = TextStyle(fontSize = 10.sp),
                        color = Color.White
                    )
                    IconButton(onClick = {
                        /*TODO*/
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



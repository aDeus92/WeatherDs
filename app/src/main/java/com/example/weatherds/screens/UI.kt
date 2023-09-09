package com.example.weatherds.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherds.data.WeatherModule
import com.example.weatherds.ui.theme.BlueLight

@Composable
fun MainList(list: List<WeatherModule>, currentDay: MutableState<WeatherModule>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(
            list
        ) { _, item ->
            ListItem(item, currentDay)
        }
    }
}

@Composable
fun ListItem(item: WeatherModule, currentDay: MutableState<WeatherModule>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 3.dp)
            .clickable {
                if (item.hours.isEmpty()) return@clickable
                currentDay.value = item
            },
        colors = CardDefaults.cardColors(BlueLight),
        elevation = CardDefaults.elevatedCardElevation(5.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(
                    start = 8.dp,
                    top = 5.dp,
                    bottom = 5.dp
                )
            ) {
                Text(
                    text = item.timeLast,
                    color = Color.White
                )
                Text(
                    text = item.condition,
                    color = Color.White
                )
            }
            Text(
                text = if (item.currentTemp.isNotEmpty())
                    item.currentTemp
                else {
                    "${item.maxTemp}/${item.minTemp}"
                },
                color = Color.White,
                style = TextStyle(fontSize = 25.sp)
            )
            AsyncImage(
                model = "https:${item.icon}",
                contentDescription = "img5",
                modifier = Modifier
                    .size(35.dp)
            )
        }
    }
}

@Composable
fun DialogSearch(dialogState: MutableState<Boolean>, onSubmit:(String)->Unit) {
    val dialogText = remember {
        mutableStateOf("")
    }
    AlertDialog(onDismissRequest = {
    dialogState.value=false
    },
        confirmButton = {
            TextButton(onClick = {
                onSubmit(dialogText.value)
                dialogState.value=false
            }) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                dialogState.value=false
            }) {
                Text(text = "Отмена")
            }
        },
        title = {
            Column (
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Введите название города:")
                TextField(value = dialogText.value, onValueChange ={
                    dialogText.value = it
                } )
            }


        }
    )
}
package com.example.weatherds

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherds.screens.MainCard
import com.example.weatherds.screens.TabLayout
import com.example.weatherds.ui.theme.WeatherDsTheme
import org.json.JSONObject

const val API_WEATHER = "608c9bc731984dcc81d112401230409"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherDsTheme {
                Image(
                    painter = painterResource(id = R.drawable.weatherbackground),
                    contentDescription = "background",
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.9f),
                    contentScale = ContentScale.FillBounds
                )
                Column {
                    MainCard()
                    TabLayout()
                }

            }
        }
    }
}

@Composable
fun Greeting(cityName: String, context: Context) {
    val state = remember {
        mutableStateOf("Неизвестно")
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),


        ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Температура в $cityName = ${state.value}"
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter

        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),

                onClick = {
                    weatherResult(cityName, state, context)

                }) {
                Text(text = "Refresh")
            }
        }
    }
}

private fun weatherResult(cityName: String, state: MutableState<String>, context: Context) {
    val url = "https://api.weatherapi.com/v1/current.json" +
            "?key=$API_WEATHER&" +
            "q=$cityName" +
            "&aqi=no"
    val queue = Volley.newRequestQueue(context)
    val stringRequest = StringRequest(
        Request.Method.GET,
        url,
        { response ->
            val obj = JSONObject(response)
            state.value = obj.getJSONObject("current").getString("temp_c") + "C"

        }, { error ->
            state.value = "УПС $error"
        }

    )
    queue.add(stringRequest)
}


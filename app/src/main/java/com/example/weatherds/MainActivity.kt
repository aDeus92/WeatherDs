package com.example.weatherds

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherds.data.WeatherModule
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
                val daysList = remember {
                    mutableStateOf(listOf<WeatherModule>())
                }
                val currentWeather = remember {
                    mutableStateOf(
                        WeatherModule(
                            "",
                            "",
                            "0.0",
                            "",
                            "",
                            "0.0",
                            "0.0",
                            ""
                        )
                    )
                }
                getData("Stavropol", this, daysList, currentWeather)
                Image(
                    painter = painterResource(id = R.drawable.weatherbackground),
                    contentDescription = "background",
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.9f),
                    contentScale = ContentScale.FillBounds
                )
                Column {

                    MainCard(currentWeather)
                    TabLayout(daysList)
                }

            }
        }
    }
}

private fun getData(
    city: String,
    context: Context,
    daysList: MutableState<List<WeatherModule>>,
    currentWeather: MutableState<WeatherModule>
) {
    val url = "https://api.weatherapi.com/v1/forecast.json" +
            "?key=$API_WEATHER" +
            "&q=$city" +
            "&days=5" +
            "&aqi=no" +
            "&alerts=no"
    val queue = Volley.newRequestQueue(context)
    val stringRequest = StringRequest(
        Request.Method.GET,
        url,
        { response ->
            val list = getWeatherModel(response)
            daysList.value = list
            currentWeather.value = list[0]
        }, { error ->
            Log.d("MyLog", "VolleyError: $error")
        }

    )
    queue.add(stringRequest)
}

private fun getWeatherModel(response: String): List<WeatherModule> {
    if (response.isEmpty()) {
        return listOf()
    }
    val mainObj = JSONObject(response)
    val city = mainObj.getJSONObject("location").getString("name")
    val list = ArrayList<WeatherModule>()
    val days = mainObj.getJSONObject("forecast").getJSONArray("forecastday")
    for (i in 0 until days.length()) {
        val item = days[i] as JSONObject
        list.add(
            WeatherModule(
                city,
                item.getString("date"),
                "",
                item.getJSONObject("day").getJSONObject("condition").getString("text"),
                item.getJSONObject("day").getJSONObject("condition").getString("icon"),
                item.getJSONObject("day").getString("maxtemp_c").toFloat().toInt()
                    .toString() + "°C",
                item.getJSONObject("day").getString("mintemp_c").toFloat().toInt()
                    .toString() + "°C",
                item.getJSONArray("hour").toString()
            )
        )

    }
    list[0] = list[0].copy(
        timeLast = mainObj.getJSONObject("current").getString("last_updated"),
        currentTemp = mainObj.getJSONObject("current").getString("temp_c").toFloat().toInt()
            .toString() + "°C"
    )
    return list
}
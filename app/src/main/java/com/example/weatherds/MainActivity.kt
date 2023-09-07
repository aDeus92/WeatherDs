package com.example.weatherds

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
                Image(
                    painter = painterResource(id = R.drawable.weatherbackground),
                    contentDescription = "background",
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.9f),
                    contentScale = ContentScale.FillBounds
                )
                Column {

                    MainCard(item = WeatherModule(
                        "Ставрополь",
                        "12:03",
                        "25°C",
                        "Partly cloudy",
                        "//cdn.weatherapi.com/weather/64x64/day/116.png",
                        "35°C",
                        "10°C",
                        "13:02"
                    ))

                    TabLayout()
                }

            }
        }
    }
}

private  fun getData(city:String, context: Context):String{
    var result:String ="def"
    val url = "https://api.weatherapi.com/v1/current.json" +
            "?key=$API_WEATHER&" +
            "&q=$city" +
            "&days=" +
            "3" +
            "&aqi=no"
    val queue = Volley.newRequestQueue(context)
    val stringRequest = StringRequest(
        Request.Method.GET,
        url,
        { response ->
        Log.d("MyLog", "resp:$response")
            val obj = JSONObject(response)
            result= obj.getJSONObject("current").getString("temp_c") + "C"

        }, { error ->
            Log.d("MyLog", "VolleyError: $error")
        }

    )
    queue.add(stringRequest)
    return result
}
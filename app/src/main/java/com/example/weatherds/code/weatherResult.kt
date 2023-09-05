package com.example.weatherds.code

import android.content.Context
import androidx.compose.runtime.MutableState
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherds.API_WEATHER
import org.json.JSONObject

private fun weatherResult(cityName: String, state: MutableState<String>, context: Context): String {
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
    return state.value
}
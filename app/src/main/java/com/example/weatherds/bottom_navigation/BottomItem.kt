package com.example.weatherds.bottom_navigation

import com.example.weatherds.R

sealed class BottomItem(
    val tittle: String,
    val iconId: Int,
    val route:String
)
{
    object Screen1: BottomItem("Screen1", R.drawable.iconbottom,"screen1")
    object Screen2: BottomItem("Screen2", R.drawable.iconbottom,"screen2")
    object Screen3: BottomItem("Screen3", R.drawable.iconbottom,"screen3")
    object Screen4: BottomItem("Screen4", R.drawable.iconbottom,"screen4")

}
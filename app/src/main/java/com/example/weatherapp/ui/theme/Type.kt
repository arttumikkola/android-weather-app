package com.example.weatherapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R


val ubuntu = FontFamily(
    Font(R.font.ubuntu_regular),
    Font(R.font.ubuntu_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    h3 = TextStyle(
        fontFamily = ubuntu,
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp
    ),
    h5 = TextStyle(
        fontFamily = ubuntu,
        fontWeight = FontWeight.Normal,
        fontSize = 27.sp
    ),
    h6 = TextStyle(
        fontFamily = ubuntu,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp
    ),
    body1 = TextStyle(
        fontFamily = ubuntu,
        fontWeight = FontWeight.Normal,
        fontSize = 19.sp
    )

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

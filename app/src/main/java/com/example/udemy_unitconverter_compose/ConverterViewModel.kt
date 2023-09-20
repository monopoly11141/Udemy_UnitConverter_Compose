package com.example.udemy_unitconverter_compose

import androidx.lifecycle.ViewModel

class ConverterViewModel : ViewModel() {

    fun getConversions() = listOf(
        Conversion(1, "Pounds To Kilograms", "lbs", "kg", 0.453592),
        Conversion(2, "Kilograms To Pounds", "kg", "lbs", 2.20462),
        Conversion(3, "Yards To Meters", "yd", "m", 0.9144),
        Conversion(4, "Meters To Yards", "m", "yd", 1.09361),
        Conversion(5, "Miles To Kilometers", "mi", "km", 1.60934),
        Conversion(6, "Kilometers To Miles", "km", "mi", 0.621371),
    )

}
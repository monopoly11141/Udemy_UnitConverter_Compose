package com.example.udemy_unitconverter_compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.udemy_unitconverter_compose.data.Conversion
import com.example.udemy_unitconverter_compose.data.ConversionResult
import com.example.udemy_unitconverter_compose.data.ConverterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConverterViewModel(
    private val converterRepository: ConverterRepository
) : ViewModel() {

    fun getConversions() = listOf(
        Conversion(1, "Pounds To Kilograms", "lbs", "kg", 0.453592),
        Conversion(2, "Kilograms To Pounds", "kg", "lbs", 2.20462),
        Conversion(3, "Yards To Meters", "yd", "m", 0.9144),
        Conversion(4, "Meters To Yards", "m", "yd", 1.09361),
        Conversion(5, "Miles To Kilometers", "mi", "km", 1.60934),
        Conversion(6, "Kilometers To Miles", "km", "mi", 0.621371),
    )

    val resultList = converterRepository.getSavedResults()

    fun addResult(message1: String, message2: String) {
        viewModelScope.launch(Dispatchers.IO) {
            converterRepository.insertResult(ConversionResult(0, message1, message2))
        }
    }

    fun removeResult(conversionResult: ConversionResult) {
        viewModelScope.launch(Dispatchers.IO) {
            converterRepository.deleteResult(conversionResult)
        }
    }

    fun clearAllResults() {
        viewModelScope.launch(Dispatchers.IO) {
            converterRepository.deleteAllResults()
        }
    }

}
package com.example.udemy_unitconverter_compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun TopScreen(converterList: List<Conversion>) {

    val selectedConversion: MutableState<Conversion?> = remember { mutableStateOf(null) }
    val inputText: MutableState<String> = remember { mutableStateOf("") }

    TopScreen_ConversionMenu(conversionList = converterList) {
        selectedConversion.value = it
    }

    selectedConversion.value?.let {
        TopScreen_InputBlock(conversion = it, inputText = inputText)
    }


}
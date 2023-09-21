package com.example.udemy_unitconverter_compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.udemy_unitconverter_compose.compose.TopScreen_ConversionMenu
import com.example.udemy_unitconverter_compose.data.Conversion
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun TopScreen(
    converterList: List<Conversion>,
    save: (String, String) -> Unit
) {

    val selectedConversion: MutableState<Conversion?> = remember { mutableStateOf(null) }
    val inputText: MutableState<String> = remember { mutableStateOf("") }
    val typedValue: MutableState<String> = remember { mutableStateOf("0.0") }

    TopScreen_ConversionMenu(conversionList = converterList) {
        selectedConversion.value = it
        typedValue.value = "0.0"
    }

    selectedConversion.value?.let { conversion ->
        TopScreen_InputBlock(conversion = conversion, inputText = inputText) { input ->
            typedValue.value = input
        }
    }

    if (typedValue.value != "0.0") {
        val input = typedValue.value.toDouble()
        val multiplyBy = selectedConversion.value!!.multiplyBy

        //rounding to 4 decimal places
        val result = input * multiplyBy
        val decimalFormat = DecimalFormat("#.####")
        decimalFormat.roundingMode = RoundingMode.DOWN

        val roundedResult = decimalFormat.format(result)

        val message1 = "${typedValue.value} ${selectedConversion.value!!.convertFrom} is equal to "
        val message2 = "$roundedResult ${selectedConversion.value!!.convertTo}"

        save(message1, message2)

        TopScreen_ResultBlock(
            message1 = message1,
            message2 = message2
        )
    }


}
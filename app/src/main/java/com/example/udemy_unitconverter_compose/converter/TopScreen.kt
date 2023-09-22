package com.example.udemy_unitconverter_compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.udemy_unitconverter_compose.compose.TopScreen_ConversionMenu
import com.example.udemy_unitconverter_compose.data.Conversion
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun TopScreen(
    converterList: List<Conversion>,
    selectedConversion: MutableState<Conversion?>,
    inputText: MutableState<String>,
    typedValue: MutableState<String>,
    isLandscape: Boolean,
    save: (String, String) -> Unit
) {

    var toSave by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {

        TopScreen_ConversionMenu(conversionList = converterList, isLandscape) {
            selectedConversion.value = it
            typedValue.value = "0.0"
        }

        selectedConversion.value?.let { conversion ->
            TopScreen_InputBlock(conversion = conversion, inputText = inputText, isLandscape) { input ->
                typedValue.value = input
                toSave = true
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
            if (toSave) {
                save(message1, message2)
                toSave = false
            }


            TopScreen_ResultBlock(
                message1 = message1,
                message2 = message2
            )
        }
    }


}
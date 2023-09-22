package com.example.udemy_unitconverter_compose.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.udemy_unitconverter_compose.ConverterViewModel
import com.example.udemy_unitconverter_compose.ConverterViewModelFactory
import com.example.udemy_unitconverter_compose.TopScreen
import com.example.udemy_unitconverter_compose.compose.history.HistoryScreen

@Composable
fun BaseScreen(
    converterViewModelFactory: ConverterViewModelFactory,
    modifier: Modifier = Modifier,
    converterViewModel: ConverterViewModel = viewModel(factory = converterViewModelFactory)
) {
    val converterList = converterViewModel.getConversions()
    val historyList = converterViewModel.resultList.collectAsState(initial = emptyList())

    val configuration = LocalConfiguration.current
    var isLandscape by remember { mutableStateOf(false) }

    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            isLandscape = false

            Column(
                modifier = Modifier
                    .padding(30.dp)
            ) {
                TopScreen(
                    converterList,
                    converterViewModel.selectedConversion,
                    converterViewModel.inputText,
                    converterViewModel.typedValue,
                    isLandscape
                ) { message1, message2 ->
                    converterViewModel.addResult(message1, message2)
                }
                SpacerComposable(20)
                HistoryScreen(
                    historyList,
                    { item -> converterViewModel.removeResult(item) },
                    { converterViewModel.clearAllResults() }
                )
            }
        }
        Configuration.ORIENTATION_LANDSCAPE -> {
            isLandscape = true

            Row(
                modifier = Modifier
                    .padding(30.dp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TopScreen(
                    converterList,
                    converterViewModel.selectedConversion,
                    converterViewModel.inputText,
                    converterViewModel.typedValue,
                    isLandscape
                ) { message1, message2 ->
                    converterViewModel.addResult(message1, message2)
                }
                Spacer(
                    modifier = modifier.width(10.dp)
                )
                HistoryScreen(
                    historyList,
                    { item -> converterViewModel.removeResult(item) },
                    { converterViewModel.clearAllResults() }
                )
            }
        }


    }

}

@Composable
fun SpacerComposable(spacerHeight: Int) {
    Spacer(
        modifier = Modifier
            .height(spacerHeight.dp)
    )
}
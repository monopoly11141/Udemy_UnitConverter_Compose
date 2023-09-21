package com.example.udemy_unitconverter_compose.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
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

    Column(
        modifier = Modifier
            .padding(30.dp)
    ) {
        TopScreen(converterList) { message1, message2 ->
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

@Composable
fun SpacerComposable(spacerHeight: Int) {
    Spacer(
        modifier = Modifier
            .height(spacerHeight.dp)
    )
}
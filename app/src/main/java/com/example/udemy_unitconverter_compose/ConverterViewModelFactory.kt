package com.example.udemy_unitconverter_compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.udemy_unitconverter_compose.data.ConverterRepository

class ConverterViewModelFactory(
    private val converterRepository: ConverterRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ConverterViewModel(converterRepository) as T
    }
}
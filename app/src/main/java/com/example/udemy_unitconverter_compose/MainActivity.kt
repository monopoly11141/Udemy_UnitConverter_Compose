package com.example.udemy_unitconverter_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.udemy_unitconverter_compose.compose.BaseScreen
import com.example.udemy_unitconverter_compose.data.ConverterDatabase
import com.example.udemy_unitconverter_compose.data.ConverterRepositoryImpl
import com.example.udemy_unitconverter_compose.ui.theme.Udemy_UnitConverter_ComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var converterViewModelFactory : ConverterViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Udemy_UnitConverter_ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    BaseScreen(converterViewModelFactory = converterViewModelFactory)
                }

            }
        }
    }
}
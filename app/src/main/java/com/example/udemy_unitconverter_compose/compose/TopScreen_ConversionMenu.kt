package com.example.udemy_unitconverter_compose.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.udemy_unitconverter_compose.data.Conversion


@Composable
fun TopScreen_ConversionMenu(
    conversionList: List<Conversion>,
    modifier: Modifier = Modifier,
    convert : ((Conversion) -> Unit)
) {
    var displayingText by remember { mutableStateOf("Selected Conversion Type : ") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var isExpanded by remember { mutableStateOf(false) }

    val icon = if (isExpanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Column(

    ) {
        OutlinedTextField(
            value = displayingText,
            onValueChange = { displayingText = it },
            textStyle = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            label = {
                Text(
                    text = "Conversion type"
                )
            },
            trailingIcon = {
                Icon(
                    icon,
                    contentDescription = "icon",
                    modifier.clickable {
                        isExpanded = !isExpanded
                    }
                )
            },
            readOnly = true,
            modifier = modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                }
        )

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            },
            modifier = modifier
                .width(with(
                    LocalDensity.current
                ) {
                    textFieldSize.width.toDp()
                })
        ) {
            conversionList.forEach { conversion ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = conversion.description,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    onClick = {
                        displayingText = conversion.description
                        isExpanded = false
                        convert(conversion)
                    }
                )
            }
        }
    }


}

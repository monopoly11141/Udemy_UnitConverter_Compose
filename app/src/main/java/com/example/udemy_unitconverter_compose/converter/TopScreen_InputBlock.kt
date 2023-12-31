package com.example.udemy_unitconverter_compose

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.udemy_unitconverter_compose.data.Conversion

@Composable
fun TopScreen_InputBlock(
    conversion: Conversion,
    inputText: MutableState<String>,
    isLandscape : Boolean,
    context: Context = LocalContext.current,
    modifier: Modifier = Modifier,
    calculate: (String) -> Unit
) {
    if(isLandscape) {
        Column(
            modifier = modifier
                .padding(0.dp, 20.dp, 0.dp, 0.dp)
        ) {
            Row(
                modifier = modifier
            ) {
                TextField(
                    value = inputText.value,
                    onValueChange = {
                        inputText.value = it
                    },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = true,
                        keyboardType = KeyboardType.Number
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.surface.copy(0.3F)
                    ),
                    textStyle = TextStyle(
                        color = Color.DarkGray,
                        fontSize = 30.sp
                    ),
                    modifier = modifier
                )

                Text(
                    text = conversion.convertFrom,
                    fontSize = 24.sp,
                    modifier = modifier
                        .padding(10.dp, 30.dp, 0.dp, 0.dp)
                )
            }

            Spacer(
                modifier = modifier
                    .height(20.dp)
            )

            OutlinedButton(

                onClick = {
                    if (inputText.value.isNotEmpty()) {
                        calculate(inputText.value)
                    } else {
                        Toast.makeText(context, "Please enter value", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = modifier
            ) {
                Text(
                    text = "Convert",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue
                )
            }

        }
    }else {
        Column(
            modifier = modifier
                .padding(0.dp, 20.dp, 0.dp, 0.dp)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                TextField(
                    value = inputText.value,
                    onValueChange = {
                        inputText.value = it
                    },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = true,
                        keyboardType = KeyboardType.Number
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.surface.copy(0.3F)
                    ),
                    textStyle = TextStyle(
                        color = Color.DarkGray,
                        fontSize = 30.sp
                    ),
                    modifier = modifier
                        .fillMaxWidth(0.65F)
                )

                Text(
                    text = conversion.convertFrom,
                    fontSize = 24.sp,
                    modifier = modifier
                        .padding(10.dp, 30.dp, 0.dp, 0.dp)
                        .fillMaxWidth(0.35F)
                )
            }

            Spacer(
                modifier = modifier
                    .height(20.dp)
            )

            OutlinedButton(

                onClick = {
                    if (inputText.value.isNotEmpty()) {
                        calculate(inputText.value)
                    } else {
                        Toast.makeText(context, "Please enter value", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Convert",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue
                )
            }

        }
    }

}
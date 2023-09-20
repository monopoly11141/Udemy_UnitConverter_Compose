package com.example.udemy_unitconverter_compose

data class Conversion(

    val id: Int,
    val description: String,
    val convertFrom: String,
    val convertTo: String,
    val multiplyBy: Double

)
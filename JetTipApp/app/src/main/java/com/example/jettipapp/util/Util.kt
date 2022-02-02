package com.example.jettipapp.util

fun calculateTotalTip(totalBill: Double, tipPercentage: Int): Double {
    return if (totalBill > 1 && totalBill.toString().isNotBlank())
        (totalBill * tipPercentage) / 100
    else 0.0
}
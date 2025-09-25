package com.example.calculator.ui

sealed class CalculatorAction {
    data class Digit(val value: Int) : CalculatorAction()
    data class Operator(val symbol: String) : CalculatorAction()
    object Dot : CalculatorAction()
    object Equals: CalculatorAction()
}
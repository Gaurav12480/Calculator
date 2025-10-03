package com.example.calculator.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class CalculatorViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CalculatorState())
    val uiState: StateFlow<CalculatorState> = _uiState.asStateFlow()

    fun onAction(action: CalculatorAction) {
        when(action) {
            is CalculatorAction.Digit -> onDigit(value = action.value)
            is CalculatorAction.Equals -> onEquals()
            is CalculatorAction.Operator -> onOperator(symbol = action.symbol)
            is CalculatorAction.Dot -> onDot()

        }
    }

    private lateinit var currentEquation: String

     private fun onDigit(value: String) {
         _uiState.value.copy(
             num1 = value
         )
        currentEquation += _uiState.value.num1
    }
    private fun onOperator(symbol: String) {
        TODO("Not yet implemented")
    }
    private fun onDot() {
        TODO("Not yet implemented")
    }
    private fun onEquals() {
        TODO("Not yet implemented")
    }
    private fun onClear() {
        TODO("Not yet implemented")
    }

    private fun onClearAll() {
        TODO("Not yet implemented")
    }
}
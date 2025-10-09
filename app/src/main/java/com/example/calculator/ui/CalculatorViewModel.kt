package com.example.calculator.ui

import androidx.compose.material3.Switch
import androidx.compose.runtime.currentComposer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


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
    companion object {
        private const val MAX_NUM_DIGIT = 8
    }

     private fun onDigit(value: String) {
         _uiState.update { currentState ->
             if (currentState.op == null) {
                 if (currentState.num1.length <= MAX_NUM_DIGIT) currentState.copy(num1 = currentState.num1 + value)
                 else currentState
             }
             else {
                 if (currentState.num2.length <= MAX_NUM_DIGIT) currentState.copy(num2 = currentState.num2 + value)
                 else currentState
             }
         }
     }
    private fun onOperator(symbol: String) {
        _uiState.update { currentState ->
            if (currentState.num1.isNotEmpty()) currentState.copy(op = symbol)
            else currentState
        }
    }

    private fun onDot() {
        TODO("Not yet implemented")
    }
    private fun onEquals() {
        TODO("Not yet implemented")
    }
    fun onClear() {
        _uiState.update { currentState ->
            when {
                currentState.num2 != "" -> currentState.copy(num2 = currentState.num2.dropLast(1))
                currentState.op != null -> currentState.copy(op = null)
                currentState.num1 != "" -> currentState.copy(num1 = currentState.num1.dropLast(1))
                else -> currentState
            }
        }
    }

    fun onClearAll() {
        _uiState.update { currentState ->
            currentState.copy(
                num1 = "",
                num2 = "",
                op = null
            )
        }
    }
}

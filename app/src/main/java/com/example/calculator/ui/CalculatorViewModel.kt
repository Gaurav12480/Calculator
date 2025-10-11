package com.example.calculator.ui

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
            if (currentState.num1.isNotEmpty() && currentState.op == null) currentState.copy(op = symbol)
            else currentState
        }
    }

    private fun onDot() {
        _uiState.update { currentState ->
            if (currentState.op == null && (!currentState.num1.contains("."))) {
                if (currentState.num1.isEmpty()) currentState.copy(num1 = currentState.num1 + "0.")
                else currentState.copy(num1 = currentState.num1 + ".")
            }
            else if (currentState.op != null && (!currentState.num2.contains("."))) {
                if (currentState.num2.isEmpty())currentState.copy(num2 = currentState.num2 + "0.")
                else currentState.copy(num2 = currentState.num2 + ".")
            }
            else {
                currentState
            }
        }
    }
    private fun onEquals() {
        _uiState.update { currentState ->
            if (currentState.op != null && currentState.num2.isNotEmpty()) {
                val n1 = currentState.num1.toFloat()
                val n2 = currentState.num2.toFloat()
                when(currentState.op) {
                    "+" -> currentState.copy(num1 = (n1 + n2).toString(), num2 = "", op = null)
                    "-" -> currentState.copy(num1 = (n1 - n2).toString(), num2 = "", op = null)
                    "×" -> currentState.copy(num1 = (n1 * n2).toString(), num2 = "", op = null)
                    else -> if (n2 != 0f) currentState.copy(num1 = (n1 / n2).toString(), num2 = "", op = null) else currentState
                }
            }
            else {
                currentState

            }
        }
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

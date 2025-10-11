package com.example.calculator.ui

import androidx.annotation.StringRes
import com.example.calculator.R

data class CalculatorButtonModel(
    @StringRes val text: Int,
    val action: CalculatorAction
)


val buttons = listOf(
    listOf(
        CalculatorButtonModel(R.string.num_7, CalculatorAction.Digit("7")),
        CalculatorButtonModel(R.string.num_8, CalculatorAction.Digit("8")),
        CalculatorButtonModel(R.string.num_9, CalculatorAction.Digit("9")),
        CalculatorButtonModel(R.string.divide, CalculatorAction.Operator("÷"))
    ),
    listOf(
        CalculatorButtonModel(R.string.num_4, CalculatorAction.Digit("4")),
        CalculatorButtonModel(R.string.num_5, CalculatorAction.Digit("5")),
        CalculatorButtonModel(R.string.num_6, CalculatorAction.Digit("6")),
        CalculatorButtonModel(R.string.multiply, CalculatorAction.Operator("×"))
    ),
    listOf(
        CalculatorButtonModel(R.string.num_1, CalculatorAction.Digit("1")),
        CalculatorButtonModel(R.string.num_2, CalculatorAction.Digit("2")),
        CalculatorButtonModel(R.string.num_3, CalculatorAction.Digit("3")),
        CalculatorButtonModel(R.string.minus, CalculatorAction.Operator("-"))
    ),
    listOf(
        CalculatorButtonModel(R.string.dot, CalculatorAction.Dot),
        CalculatorButtonModel(R.string.num_0, CalculatorAction.Digit("0")),
        CalculatorButtonModel(R.string.plus, CalculatorAction.Operator("+")),
        CalculatorButtonModel(R.string.equals, CalculatorAction.Equals)
    )
)

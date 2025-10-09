package com.example.calculator.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.R
import kotlin.math.sin

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier,
    calculatorViewModel: CalculatorViewModel = viewModel()
) {
    val calculatorUiState by calculatorViewModel.uiState.collectAsState()

    Column (
        modifier = modifier
            .fillMaxSize()
            .safeContentPadding()
            .padding(20.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //Output
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()

        ) {
            Text(
                text = calculatorUiState.num1 + (calculatorUiState.op
                    ?: "") + calculatorUiState.num2,
                fontSize = 50.sp,
                lineHeight = 50.sp,
                textAlign = TextAlign.End
            )
        }

        //Cancel Button
        Row (
            modifier = Modifier
                .fillMaxWidth()
            ,
            horizontalArrangement = Arrangement.End
        ){
            CancelButton(modifier = Modifier, onClick = { calculatorViewModel.onClear() }, onLongClick = { calculatorViewModel.onClearAll() }, text = R.string.cancel)
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), thickness = 2.dp, DividerDefaults.color)

        //Keypad
        buttons.forEach { row ->
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                row.forEach { ele ->
                    when (ele.action) {
                        is CalculatorAction.Digit -> {
                            CalculatorButton(
                                calculatorButtonModel = ele, buttonColors = ButtonDefaults.buttonColors(
                                    contentColor = MaterialTheme.colorScheme.onSecondary,
                                    containerColor = MaterialTheme.colorScheme.secondary),
                                modifier = Modifier
                                    .weight(1f)       //Stretches to fill width
                                    .aspectRatio(1f), //Makes height equal to width
                                onClick = { calculatorViewModel.onAction(ele.action) }

                            )
                        }
                        CalculatorAction.Equals -> {
                            CalculatorButton(
                                calculatorButtonModel = ele,
                                buttonColors = ButtonDefaults.buttonColors(
                                    contentColor = MaterialTheme.colorScheme.onPrimary,
                                    containerColor = MaterialTheme.colorScheme.primary),
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f),
                                onClick = { calculatorViewModel.onAction(ele.action) }
                            )
                        }
                        is CalculatorAction.Operator, CalculatorAction.Dot -> {
                            CalculatorButton(
                                calculatorButtonModel = ele,
                                buttonColors = ButtonDefaults.buttonColors(
                                    contentColor = MaterialTheme.colorScheme.tertiary,
                                    containerColor = MaterialTheme.colorScheme.onTertiary),
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f),
                                onClick = { calculatorViewModel.onAction(ele.action) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(modifier: Modifier = Modifier, calculatorButtonModel: CalculatorButtonModel, buttonColors: ButtonColors, onClick: ()-> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(4.dp)
        ,
        colors = ButtonDefaults.buttonColors(
            contentColor = buttonColors.contentColor,
            containerColor = buttonColors.containerColor
        ),
        shape = CircleShape,
        contentPadding = PaddingValues(8.dp),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline)
    ) {

        Text(
            text = stringResource(calculatorButtonModel.text),
            fontSize = 36.sp,
        )
    }
}

@Composable
fun CancelButton(modifier: Modifier, onClick: () -> Unit, onLongClick: () -> Unit, text: Int) {
    Text(
        text = stringResource(R.string.cancel),
        fontSize = 38.sp,
        modifier = modifier
            .combinedClickable(onClick = onClick, onLongClick = onLongClick)
    )
}


@Composable
@Preview
fun CalculatorScreenPreview() {
    CalculatorScreen()
}
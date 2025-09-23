package com.example.calculator.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.R

@Composable
fun CalculatorScreen(modifier: Modifier) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            CancelButton(modifier = Modifier, onClick = {}, onLongClick = {}, text = R.string.cancel)
        }
        HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

    }
}

@Composable
fun CalculatorButton(modifier: Modifier = Modifier, onClick: () -> Unit, text: Int) {
    Button(
        onClick = onClick,
        modifier = modifier.wrapContentSize(),
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.onPrimary,
            containerColor = MaterialTheme.colorScheme.primary
        ),
        shape = CircleShape,
        contentPadding = PaddingValues(8.dp)
    ) {

        Text(
            text = stringResource(text),
            fontSize = 36.sp,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CancelButton(modifier: Modifier, onClick: () -> Unit, onLongClick: () -> Unit, text: Int) {
    Text(
        text = stringResource(R.string.cancel),
        fontSize = 32.sp,
        modifier = modifier.combinedClickable(onClick = onClick, onLongClick = onLongClick)
    )
}


@Composable
@Preview
fun CalculatorScreenPreview() {
    CalculatorScreen(modifier = Modifier)
}
package com.example.loginsignuppractice.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomButton(text: String, onClickAction: () -> Unit) {
    //var enabled by remember { mutableStateOf(enabled) }

    Button(
        onClick = onClickAction,
        //enabled = enabled,
        modifier = Modifier
            .wrapContentSize()
            .fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
        ) {
        Text(
            "$text",
            style = TextStyle(
                color = Color.White,
                fontSize = 15.sp
            )
        )
    }
}
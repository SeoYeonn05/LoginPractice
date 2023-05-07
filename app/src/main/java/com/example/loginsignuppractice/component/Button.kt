package com.example.loginsignuppractice.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomButton(text: String) {
    Button(
        onClick = {},
        modifier = Modifier.wrapContentSize().width(240.dp),
        shape = RoundedCornerShape(12.dp)
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
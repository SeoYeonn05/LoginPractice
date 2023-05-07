package com.example.loginsignuppractice.page

import BasicUi
import DuckieTextField
import IconTextField
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginsignuppractice.ui.theme.mainColor
import com.example.loginsignuppractice.widget.CustomButton

class NumberLoginPage {
    @Composable
    fun NumberLoginUi(navController: NavController) {
        BasicUi(
            true,
            navController,
            "Login with number",
            "Your number will safe with us. We won;t share your details with anyone.",
            ui = { NumberLoginContent(navController) },
            backAction = {
                navController.popBackStack()
            })
    }
}

// Composable이 클래스 안에 있는 것과 밖에 있는 게 차이가 있을까?
@Composable
fun NumberLoginContent(navController: NavController) {
    var phoneNum by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconTextField(
            imageVector = Icons.Default.Phone,
            contentType = "Enter mobile number",
            textFieldValue = phoneNum
        )
        Spacer(modifier = Modifier.height(20.dp))
        DuckieTextField()
        Spacer(modifier = Modifier.height(30.dp))
        CustomButton(text = "Next") {
            //navController.navigate(Rout)
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row() {
            Text(
                "Not received Code? ",
                style = TextStyle(
                    fontSize = 10.sp
                )
            )
            ClickableText(
                text = AnnotatedString(
                    "Resend",
                    spanStyle = SpanStyle(
                        fontSize = 10.sp,
                        color = mainColor
                    )
                ),
                onClick = {})
        }
    }
}
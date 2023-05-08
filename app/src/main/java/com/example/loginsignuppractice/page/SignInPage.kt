package com.example.loginsignuppractice.page

import BasicUi
import EmailTextField
import PasswordTextField
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import checkEmail
import checkPassword
import com.example.loginsignuppractice.ui.theme.mainColor
import com.example.loginsignuppractice.widget.CustomButton

class SignInPage {
    @Composable
    fun SignInUi(navController: NavController) {
        BasicUi(
            true,
            navController,
            "Sign In",
            "Sign in to discover amazing near around you.",
            ui = { SignInContent(navController) },
            backAction = {
                navController.popBackStack()
            })
    }
}

@Composable
fun SignInContent(navController: NavController) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var pw by remember { mutableStateOf(TextFieldValue("")) }


    EmailTextField(email)
    Spacer(modifier = Modifier.height(10.dp))

    PasswordTextField(pw, "Password")
    Spacer(modifier = Modifier.height(20.dp))
    CustomButton(text = "Sign In"){}
    ClickableText(
        text = AnnotatedString(
            "Forgot Password?",
            spanStyle = SpanStyle(
                fontSize = 10.sp,
                color = mainColor
            )
        ),
        onClick = {

        })
}


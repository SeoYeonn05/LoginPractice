package com.example.loginsignuppractice.page

import BasicUi
import IconTextField
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import com.example.loginsignuppractice.widget.CustomButton

class SignInPage {
    @Composable
    fun SignInUi() {
        BasicUi(
            "Sign In",
            "Sign in to discover amazing near around you."
        ) { SignInContent() }
    }
}

@Composable
fun SignInContent() {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var pw by remember { mutableStateOf(TextFieldValue("")) }

    Column() {
        IconTextField(
            Icons.Default.Email,
            "Email",
            email
        )
        IconTextField(
            Icons.Default.Lock,
            "Password",
            pw
        )
        CustomButton(text = "Sign In")
        TextButton(
            onClick = {}
        ) {
            Text("Forgot Password?")
        }
    }
}

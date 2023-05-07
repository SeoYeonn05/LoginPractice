package com.example.loginsignuppractice.page

import BasicUi
import EmailTextField
import PasswordTextField
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.loginsignuppractice.Route
import com.example.loginsignuppractice.widget.CustomButton

class SignInPage {
    @Composable
    fun SignInUi(navController: NavController) {
        BasicUi(
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

    PasswordTextField(pw)
    Spacer(modifier = Modifier.height(20.dp))
    CustomButton(text = "Sign In"){

    }
    TextButton(
        onClick = {}
    ) {
        Text("Forgot Password?")
    }
}


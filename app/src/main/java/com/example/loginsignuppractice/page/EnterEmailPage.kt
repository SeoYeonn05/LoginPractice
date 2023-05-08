package com.example.loginsignuppractice.page

import BasicUi
import EmailTextField
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.loginsignuppractice.Route
import com.example.loginsignuppractice.widget.CustomButton


@Composable
fun EnterEmail(navController: NavController) {
    BasicUi(
        false,
        navController,
        "Find your account",
        "Please enter your email address of phone number to search for your account.",
        ui = { EnterEmailContent(navController) },
        backAction = {
            navController.popBackStack()
        })
}


@Composable
fun EnterEmailContent(navController: NavController) {
    var email by rememberSaveable { mutableStateOf("") }

    EmailTextField(
        onTextChanged = {email = it}
    )
    Spacer(modifier = Modifier.height(20.dp))
    CustomButton(text = "Search") {
        navController.navigate(Route.RecoverPassword.routes)
    }
}

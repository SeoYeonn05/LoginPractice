package com.example.loginsignuppractice.page

import BasicUi
import PasswordTextField
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

class NewPassword {
    @Composable
    fun NewPasswordUi(navController: NavController) {
        BasicUi(
            false,
            navController,
            "Create new password",
            "Create a new password and please never share it with anyone for safe use.",
            ui = { NewPasswordContent(navController) },
            backAction = {
                navController.popBackStack()
            })
    }


    @Composable
    fun NewPasswordContent(navController: NavController) {
        var pw by rememberSaveable { mutableStateOf("") }
        var newPw by rememberSaveable { mutableStateOf("") }


        PasswordTextField(onTextChanged = { pw = it }, "New Password")
        Spacer(modifier = Modifier.height(20.dp))
        PasswordTextField(onTextChanged = { newPw = it }, "Confirm New Password")
        Spacer(modifier = Modifier.height(30.dp))
        CustomButton(text = "Update Password") {
            navController.navigate(Route.ChangedPassword.routes)
        }

    }
}
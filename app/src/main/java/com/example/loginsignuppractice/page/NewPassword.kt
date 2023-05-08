package com.example.loginsignuppractice.page

import BasicUi
import PasswordTextField
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
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
            true,
            navController,
            "Find your account",
            "Please enter your email address of phone number to search for your account.",
            ui = { NewPasswordContent(navController) },
            backAction = {
                navController.popBackStack()
            })
    }


    @Composable
    fun NewPasswordContent(navController: NavController) {
        var pw by remember { mutableStateOf(TextFieldValue("")) }
        var newPw by remember { mutableStateOf(TextFieldValue("")) }


        PasswordTextField(pw, "New Password")
        Spacer(modifier = Modifier.height(20.dp))
        PasswordTextField(pw, "Confirm New Password")
        CustomButton(text = "Update Password") {
            navController.navigate(Route.NewPassword.routes)
        }

    }

    private fun checkPassword(pw: String, newPw: String): Boolean {
        if (pw == newPw) return true
        return false
    }
}
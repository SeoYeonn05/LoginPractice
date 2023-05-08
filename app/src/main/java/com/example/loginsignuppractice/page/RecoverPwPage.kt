package com.example.loginsignuppractice.page

import BasicUi
import EmailTextField
import PhoneNumField
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import checkEmail
import com.example.loginsignuppractice.Route
import com.example.loginsignuppractice.widget.CustomButton

class RecoverPwPage {
    @Composable
    fun RecoverPwUi(navController: NavController) {
        BasicUi(
            true,
            navController,
            "Recover your password",
            "Select credentials which should we use to recover your password.",
            ui = { RecoverPwContent(navController) },
            backAction = {
                navController.popBackStack()
            })
    }

    @Composable
    fun RecoverPwContent(navController: NavController) {
        var email by remember { mutableStateOf(TextFieldValue("")) }
        var phoneNum by remember { mutableStateOf(TextFieldValue("")) }

        PhoneNumField(
            textFieldValue = phoneNum,
            mask = "000 0000 0000",
            maskNumber = '0',)
        Spacer(modifier = Modifier.height(10.dp))
        EmailTextField(textFieldValue = email)
        Spacer(modifier = Modifier.height(30.dp))
        CustomButton(text = "Recover Password") {
            navController.navigate(Route.EnterOTP.routes)
        }
    }
}
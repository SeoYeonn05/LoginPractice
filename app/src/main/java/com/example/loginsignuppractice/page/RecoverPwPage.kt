package com.example.loginsignuppractice.page

import BasicUi
import EmailTextField
import PhoneNumField
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.loginsignuppractice.Route
import com.example.loginsignuppractice.component.CustomButton

class RecoverPwPage {
    @Composable
    fun RecoverPwUi(navController: NavController) {
        BasicUi(
            false,
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
        var email by rememberSaveable { mutableStateOf("") }
        var phoneNum by remember { mutableStateOf(TextFieldValue("")) }

        PhoneNumField(
            textFieldValue = phoneNum,
            mask = "000 0000 0000",
            maskNumber = '0',)
        Spacer(modifier = Modifier.height(10.dp))
        EmailTextField(
            onTextChanged = {email = it}
        )
        Spacer(modifier = Modifier.height(30.dp))
        CustomButton(text = "Recover Password") {
            //왜 안되는건지 모르겠음
            val bundle = bundleOf("phoneNum" to phoneNum.text)

            navController.navigate("${Route.EnterOTP.routes}/$bundle")
        }
    }
}
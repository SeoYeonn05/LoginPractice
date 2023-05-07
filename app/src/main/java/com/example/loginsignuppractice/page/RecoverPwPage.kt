package com.example.loginsignuppractice.page

import BasicUi
import EmailTextField
import IconTextField
import PhoneNumField
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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

        PhoneNumField(
            mask = "000 0000 0000",
            maskNumber = '0',)
        Spacer(modifier = Modifier.height(20.dp))
        EmailTextField(textFieldValue = email)
        CustomButton(text = "Recover Password") {
            navController.navigate(Route.OTP.routes)
        }
    }
}
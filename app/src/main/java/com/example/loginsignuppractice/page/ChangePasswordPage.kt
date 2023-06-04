package com.example.loginsignuppractice.page

import com.example.loginsignuppractice.repository.AuthRepository

import BasicUi
import EmailTextField
import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginsignuppractice.Route
import com.example.loginsignuppractice.component.CustomButton
import com.example.loginsignuppractice.ui.theme.mainColor


@Composable
fun ChangePasswordUi(authRepository: AuthRepository, navController: NavController) {
    BasicUi(
        false,
        navController,
        "Change new password",
        "Create a new password and please never share it with anyone for safe use.",
        ui = { changePasswordContent(authRepository = authRepository, navController) },
        backAction = {
            navController.popBackStack()
        })
}


@Composable
fun changePasswordContent(authRepository: AuthRepository, navController: NavController) {
    var email by rememberSaveable { mutableStateOf("") }

    EmailTextField(
        onTextChanged = {email = it}
    )
    Spacer(modifier = Modifier.height(20.dp))
    CustomButton(text = "Send Email") {
        authRepository.resetPw(navController, email)
    }
    Spacer(modifier = Modifier.height(10.dp))

    ClickableText(
        text = AnnotatedString(
            "Login Again",
            spanStyle = SpanStyle(
                fontSize = 10.sp,
                color = mainColor
            )
        ),
        onClick = {
            navController.navigate(Route.SignIn.routes)
        })
}

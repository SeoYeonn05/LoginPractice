package com.example.loginsignuppractice.page

import BasicUi
import EmailTextField
import IconTextField
import PasswordTextField
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginsignuppractice.ui.theme.mainColor
import com.example.loginsignuppractice.component.CustomButton
import com.example.loginsignuppractice.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SignUpUi(navController: NavController) {

    BasicUi(
        true,
        navController,
        "Sign Up",
        "Sign up to discover amazing near around you.",
        ui = { SignUpContent(navController) },
        backAction = {
            navController.popBackStack()
        })
}

@Composable
fun SignUpContent(navController: NavController) {
    val authRepository = AuthRepository()
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var email by rememberSaveable { mutableStateOf("") }
    var pw by rememberSaveable { mutableStateOf("") }


    // 왜 column arrangement로 간격이 안 벌어질까?
    IconTextField(
        imageVector = Icons.Default.Person,
        contentType = "Full Name",
        textFieldValue = username
    )
    Spacer(modifier = Modifier.height(10.dp))
    EmailTextField(
        onTextChanged = { email = it }
    )
    Spacer(modifier = Modifier.height(10.dp))
    PasswordTextField(onTextChanged = { pw = it }, "Password")
    Spacer(modifier = Modifier.height(30.dp))
    CustomButton(text = "Sign Up") {
        Log.d(TAG, "Sign Up")
        if (authRepository != null) {
            Log.d(TAG, "click createUserWithEmail")
            authRepository.signUp(navController, email, pw)
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
    Row() {
        Text(
            "By signing up you accpet out ",
            style = TextStyle(
                fontSize = 10.sp
            )
        )
        ClickableText(
            text = AnnotatedString(
                "Terms of Services ",
                spanStyle = SpanStyle(
                    fontSize = 10.sp,
                    color = mainColor
                )
            ),
            onClick = {})
        Text(
            "and",
            style = TextStyle(
                fontSize = 10.sp
            )
        )

    }
    ClickableText(
        text = AnnotatedString(
            "Private Policy",
            spanStyle = SpanStyle(
                fontSize = 10.sp,
                color = mainColor
            )
        ),
        onClick = {})
}


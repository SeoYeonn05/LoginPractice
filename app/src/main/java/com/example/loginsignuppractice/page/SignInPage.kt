package com.example.loginsignuppractice.page

import BasicUi
import EmailTextField
import PasswordTextField
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import checkEmail
import checkPassword
import com.example.loginsignuppractice.Route
import com.example.loginsignuppractice.ui.theme.mainColor
import com.example.loginsignuppractice.widget.CustomButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.components.Component
import com.google.firebase.ktx.Firebase

class SignInPage: ComponentActivity() {

    fun SignIn(navController : NavController, email: String, password: String, auth: FirebaseAuth) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        finish()
                        navController.navigate(Route.Main.routes)
                    } else {
                    }
                }
        }
    }
    @Composable
    fun SignInUi(navController: NavController, auth: FirebaseAuth) {
        BasicUi(
            true,
            navController,
            "Sign In",
            "Sign in to discover amazing near around you.",
            ui = { SignInContent(navController, auth) },
            backAction = {
                navController.popBackStack()
            })
    }
    @Composable
    fun SignInContent(navController: NavController, auth: FirebaseAuth) {
        var email by rememberSaveable { mutableStateOf("") }
        var pw by rememberSaveable { mutableStateOf("") }


        EmailTextField(
            onTextChanged = {email = it}
        )
        Spacer(modifier = Modifier.height(10.dp))

        PasswordTextField(            onTextChanged = {pw = it}
            , "Password")
        Spacer(modifier = Modifier.height(20.dp))
        CustomButton(text = "Sign In"){
            SignIn(navController, email, pw, auth)

        }
        ClickableText(
            text = AnnotatedString(
                "Forgot Password?",
                spanStyle = SpanStyle(
                    fontSize = 10.sp,
                    color = mainColor
                )
            ),
            onClick = {

            })
    }
}




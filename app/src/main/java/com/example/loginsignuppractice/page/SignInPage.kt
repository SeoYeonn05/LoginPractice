package com.example.loginsignuppractice.page

import BasicUi
import EmailTextField
import PasswordTextField
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginsignuppractice.Route
import com.example.loginsignuppractice.ui.theme.mainColor
import com.example.loginsignuppractice.component.CustomButton
import com.example.loginsignuppractice.repository.AuthRepository
import com.example.loginsignuppractice.viewModel.SignInViewModel
import com.google.firebase.auth.FirebaseAuth

class SignInPage : ComponentActivity() {
    var viewModel: SignInViewModel = SignInViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.pref = getSharedPreferences("pref", MODE_PRIVATE)
    }



    @Composable
    fun SignInUi(navController: NavController, auth: FirebaseAuth) {
        var autoLogin = viewModel.pref?.getBoolean("AutoLoginChecked", false) ?: false

        Log.d("SharedPreference", autoLogin.toString())

        if (auth.currentUser != null && autoLogin) {
            navController.navigate(Route.Main.routes)
        } else{
            BasicUi(
                true,
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
        val authRepository = AuthRepository()
        var email by rememberSaveable { mutableStateOf("") }
        var pw by rememberSaveable { mutableStateOf("") }
        //var isChecked by remember { mutableStateOf(false) }


        EmailTextField(
            onTextChanged = { email = it }
        )
        Spacer(modifier = Modifier.height(10.dp))

        PasswordTextField(onTextChanged = { pw = it }, "Password")
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Auth Login?",
                style = TextStyle(
                    fontSize = 12.sp,
                    color = mainColor
                )
            )
            Spacer(modifier = Modifier.width(20.dp))
            Switch(
                checked = viewModel.isChecked.value,
                onCheckedChange = {
                    viewModel.isChecked.value = it
                    viewModel.pref?.edit()?.apply {
                        putBoolean("AutoLoginChecked", it)
                        apply()
                        Log.d("SharedPreference",
                            viewModel.pref?.getBoolean("AutoLoginChecked", false).toString()
                        )
                    }

                },
                colors = SwitchDefaults.colors(uncheckedThumbColor = mainColor),

                )
        }



        CustomButton(text = "Sign In") {
            viewModel.signInUser(navController, email, pw)

        }
        Spacer(modifier = Modifier.height(10.dp))
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




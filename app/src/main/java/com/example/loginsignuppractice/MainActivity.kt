package com.example.loginsignuppractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginsignuppractice.page.*
import com.example.loginsignuppractice.ui.theme.LoginSignUpPracticeTheme
import com.example.loginsignuppractice.ui.theme.backgroundColor
import java.util.*

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginSignUpPracticeTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = backgroundColor
                ) {
                    Screen()
                }
            }
        }
    }
}

@Composable
fun Screen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.Start.routes) {
        composable(Route.Start.routes) {
            StartUI(navController = navController)
        }
        composable(Route.SignIn.routes) {
            SignInPage().SignInUi(navController = navController)
        }
        composable(Route.SignUp.routes) {
            SignUpPage().SignUpUi(navController = navController)
        }
        composable(Route.NumberLogin.routes) {
            NumberLoginUi(navController =  navController)
        }
        composable(Route.EnterEmail.routes) {
            EnterEmail(navController =  navController)
        }
        composable(Route.Main.routes) {
            Main(navController =  navController)
        }
        composable(Route.RecoverPassword.routes) {
            RecoverPwPage().RecoverPwUi(navController =  navController)
        }
        composable(Route.OTP.routes) {
            RecoverPwPage().RecoverPwUi(navController =  navController)
        }

    }
}

@Composable
fun Main(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        androidx.compose.material.Text(
            "메인 페이지",
            style = TextStyle(
                color = Color.White
            ),
            textAlign = TextAlign.Center
        )
    }
}






@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginSignUpPracticeTheme {
    }
}
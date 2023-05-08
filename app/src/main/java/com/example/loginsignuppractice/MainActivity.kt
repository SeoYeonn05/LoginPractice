package com.example.loginsignuppractice

import ChangedPasswordUi
import EnterOTP
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.loginsignuppractice.page.*
import com.example.loginsignuppractice.ui.theme.LoginSignUpPracticeTheme
import com.example.loginsignuppractice.ui.theme.backgroundColor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*

class MainActivity : ComponentActivity() {
    private var auth: FirebaseAuth? = null


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        setContent {
            LoginSignUpPracticeTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = backgroundColor
                ) {
                    Screen(auth!!)
                }
            }
        }
    }
}

@Composable
fun Screen(auth: FirebaseAuth) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.Start.routes) {
        composable(Route.Start.routes) {
            StartPage().StartUI(navController = navController)
        }
        composable(Route.SignIn.routes) {
            SignInPage().SignInUi(navController = navController, auth)
        }
        composable(Route.SignUp.routes) {
            SignUpUi(navController = navController, auth)
        }
        composable(Route.NumberLogin.routes) {
            NumberLoginUi(navController = navController)
        }
        composable(Route.EnterEmail.routes) {
            EnterEmail(navController = navController)
        }
        composable(Route.Main.routes) {
            Main(navController = navController)
        }
        composable(Route.RecoverPassword.routes) {
            RecoverPwPage().RecoverPwUi(navController = navController)
        }
        composable("${Route.EnterOTP.routes}/{phoneNum}" , arguments = listOf(
            navArgument("phoneNum"){
                type = NavType.StringType
            }
        )) {
            val phoneNum = it.arguments?.getString("phoneNum")

            if (phoneNum != null) {
                EnterOTP(navController = navController, phoneNum)
            }
        }
        composable(Route.NewPassword.routes) {
            NewPassword().NewPasswordUi(navController = navController)
        }
        composable(Route.ChangedPassword.routes) {
            ChangedPasswordUi(navController = navController)
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
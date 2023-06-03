package com.example.loginsignuppractice

import ChangedPasswordUi
import EnterOTP
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.loginsignuppractice.page.*
import com.example.loginsignuppractice.repository.AuthRepository
import com.example.loginsignuppractice.repository.UserRepository
import com.example.loginsignuppractice.ui.theme.LoginSignUpPracticeTheme
import com.example.loginsignuppractice.ui.theme.backgroundColor
import java.util.*

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoginSignUpPracticeTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = backgroundColor
                ) {
                    Screen(context = applicationContext)
                }
            }
        }
    }
}

@Composable
fun Screen(context: Context) {
    val navController = rememberNavController()
    var authRepository = AuthRepository(context = context)
    var userRepository = UserRepository(context = context)

    NavHost(navController = navController, startDestination = Route.Start.routes) {
        composable(Route.Start.routes) {
            StartPage(authRepository, navController = navController).StartUI()
        }
        composable(Route.SignIn.routes) {
            SignInPage(authRepository, userRepository).SignInUi(navController = navController)
        }
        composable(Route.SignUp.routes) {
            SignUpUi(userRepository, authRepository, navController = navController)
        }
        composable(Route.NumberLogin.routes) {
            NumberLoginUi(navController = navController)
        }
        composable(Route.EnterEmail.routes) {
            EnterEmail(navController = navController)
        }
        composable(Route.Main.routes) {
            MainPage().Main(navController = navController)
        }
        composable(Route.RecoverPassword.routes) {
            RecoverPwPage().RecoverPwUi(navController = navController)
        }
        composable("${Route.EnterOTP.routes}/{phoneNum}", arguments = listOf(
            navArgument("phoneNum") {
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
        composable(Route.ChangePassword.routes) {
            ChangePasswordUi(context, navController = navController)
        }
        composable(Route.RegisterUserInfo.routes) {
            registerUserInfoUi(userRepository, navController = navController)
        }
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginSignUpPracticeTheme {
    }
}
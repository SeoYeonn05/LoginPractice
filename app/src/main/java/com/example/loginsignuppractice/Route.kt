package com.example.loginsignuppractice

sealed class Route(val routes: String){
    object Main: Route("Main")
    object SignIn: Route("SignIn")
    object SignUp: Route("SignUp")
    object Login: Route("Login")
    object FindAccount: Route("FindAccount")
    object RecoverPassword: Route("RecoverPassword")
    object OTP: Route("OTP")
    object NewPassword: Route("NewPassword")
    object ChangePassword: Route("ChangePassword")
}



package com.example.loginsignuppractice

sealed class Route(val routes: String){
    object Start: Route("Start")
    object SignIn: Route("SignIn")
    object SignUp: Route("SignUp")
    object NumberLogin: Route("NumberLogin")
    object Main: Route("Main")
    object EnterEmail: Route("EnterEmail")
    object RecoverPassword: Route("RecoverPassword")
    object OTP: Route("OTP")
    object NewPassword: Route("NewPassword")
    object ChangePassword: Route("ChangePassword")
}



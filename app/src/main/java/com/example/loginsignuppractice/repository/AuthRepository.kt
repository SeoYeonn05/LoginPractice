package com.example.loginsignuppractice.repository

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.loginsignuppractice.Route
import com.example.loginsignuppractice.Screen
import com.example.loginsignuppractice.ui.theme.LoginSignUpPracticeTheme
import com.example.loginsignuppractice.ui.theme.backgroundColor
import com.example.loginsignuppractice.util.SharedPreferenceUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class AuthRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val _userLiveData = MutableLiveData<FirebaseUser>()
    val userLiveData: LiveData<FirebaseUser>
        get() = _userLiveData

    fun getCurrentUser(): FirebaseUser? {
        Log.d("getCurrentUser autoId",firebaseAuth.currentUser!!.uid )
        return firebaseAuth.currentUser
    }
    fun signUp(
        navController: NavController,
        email: String,
        pw: String
    ) {
        Log.d(ContentValues.TAG, "email: ${email}, pw: ${pw}")

        if (email.isNotBlank() && pw.isNotBlank()) {
            Log.d(ContentValues.TAG, "createUserWithEmail:start")

            firebaseAuth?.createUserWithEmailAndPassword(email, pw)?.addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")
                    Log.d("SignUp autoId", firebaseAuth.currentUser!!.uid)

                    navController.navigate(Route.SignIn.routes)
                } else {
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", it.exception)


                }
            }
        }

        //SharedPreferenceUtil().saveString(context = context, "uid", firebaseAuth.currentUser!!.uid)
    }

    fun signIn(navController: NavController, email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            firebaseAuth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        navController.navigate(Route.RegisterUserInfo.routes)
                    } else {
                    }
                }
        }
    }

    fun resetPw(navController: NavController, email: String): String{
        var str = ""
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {
            str = if(it.isSuccessful){
                navController.navigate(Route.SignIn.routes)
                "Please reset your password and log in again"
            } else{
                ""
            }
        }

        return str
    }

    fun googleLogin(idToken:String){
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful){
                _userLiveData.postValue(firebaseAuth.currentUser)
            }else{
                // 실패 처리
            }
        }
    }
}

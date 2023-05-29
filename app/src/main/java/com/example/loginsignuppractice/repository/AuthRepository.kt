package com.example.loginsignuppractice.repository

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.loginsignuppractice.R
import com.example.loginsignuppractice.Route
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class AuthRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val _userLiveData = MutableLiveData<FirebaseUser>()
    val userLiveData: LiveData<FirebaseUser>
        get() = _userLiveData


    fun checkCurrentUser(): Boolean{
        val currentUser = firebaseAuth.currentUser
        return currentUser != null
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

                    navController.navigate(Route.SignIn.routes)
                } else {
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", it.exception)


                }
            }
        }
    }

    fun signIn(navController: NavController, email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            firebaseAuth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        navController.navigate(
                            Route.Main.routes,
                            NavOptions.Builder()
                                .setPopUpTo(Route.Main.routes, true)
                                .build()
                        )
                        navController.navigate(Route.Main.routes)
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

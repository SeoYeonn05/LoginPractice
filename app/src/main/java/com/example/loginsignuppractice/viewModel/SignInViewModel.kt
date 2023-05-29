package com.example.loginsignuppractice.viewModel

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.loginsignuppractice.Route
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import com.example.loginsignuppractice.repository.AuthRepository
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseUser


class SignInViewModel: ViewModel() {
    var pref: SharedPreferences? = null
    var isChecked = mutableStateOf(false)

    private var authRepository: AuthRepository = AuthRepository()
    private val _userLiveData = authRepository.userLiveData

    val userLiveData: LiveData<FirebaseUser>
        get() = _userLiveData


    fun signInUser(navController: NavController, email: String, pw: String){
        authRepository.signIn(navController, email, pw)
    }
}


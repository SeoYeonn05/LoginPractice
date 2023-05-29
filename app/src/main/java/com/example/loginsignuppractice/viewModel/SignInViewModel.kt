package com.example.loginsignuppractice.viewModel

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.loginsignuppractice.Route
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.loginsignuppractice.repository.AuthRepository
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseUser


class SignInViewModel: ViewModel() {
    var pref: SharedPreferences? = null
    private var _isChecked = MutableLiveData<Boolean>()
    val isChecked: LiveData<Boolean>
        get() = _isChecked
    // set 함수는 이렇게 만드는 게 맞나? 아니면 더 간단한 방법이 있을까?
    fun setCheckedValue(value: Boolean) {
        _isChecked.value = value
    }


    private var authRepository: AuthRepository = AuthRepository()
    private val _userLiveData = authRepository.userLiveData

    val userLiveData: LiveData<FirebaseUser>
        get() = _userLiveData


    fun signInUser(navController: NavController, email: String, pw: String){
        authRepository.signIn(navController, email, pw)
    }

    fun isAutoLogin(){
        if(isChecked.value == true){
            pref?.edit()?.apply {
                putBoolean("AutoLoginChecked", true)
                apply()
                Log.d("SharedPreference",
                    pref?.getBoolean("AutoLoginChecked", true).toString()
                )
            }
        }
        else{
            pref?.edit()?.apply {
                putBoolean("AutoLoginChecked", false)
                apply()
                Log.d("SharedPreference",
                    pref?.getBoolean("AutoLoginChecked", false).toString()
                )
            }
        }

    }
}


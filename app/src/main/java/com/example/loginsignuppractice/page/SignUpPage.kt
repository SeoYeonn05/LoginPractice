package com.example.loginsignuppractice.page

import BasicUi
import EmailTextField
import IconTextField
import PasswordTextField
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginsignuppractice.widget.CustomButton

class SignUpPage {
    @Composable
    fun SignUpUi(navController: NavController) {
        BasicUi(
            "Sign Up",
            "Sign up to discover amazing near around you."
        ) { SignUpContent(navController) }
    }

    @Composable
    fun SignUpContent(navController: NavController) {
        var username by remember { mutableStateOf(TextFieldValue("")) }
        var email by remember { mutableStateOf(TextFieldValue("")) }
        var pw by remember { mutableStateOf(TextFieldValue("")) }


        // 왜 column arrangement로 간격이 안 벌어질까?
        IconTextField(
            imageVector = Icons.Default.Person,
            contentType = "Full Name",
            textFieldValue = username
        )
        Spacer(modifier = Modifier.height(10.dp))
        EmailTextField(email)
        Spacer(modifier = Modifier.height(10.dp))
        PasswordTextField(pw)
        Spacer(modifier = Modifier.height(30.dp))
        CustomButton(text = "Sign Up")
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "By signing up you accpet out Terms of SErvice and",
            style = TextStyle(
                fontSize = 10.sp
            )
        )
        Text(
            "Private Policy",
            style = TextStyle(
                fontSize = 10.sp
            )
        )

    }


}
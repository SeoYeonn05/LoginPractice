package com.example.loginsignuppractice.page

import BasicUi
import EmailTextField
import IconTextField
import PasswordTextField
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginsignuppractice.Route
import com.example.loginsignuppractice.ui.theme.mainColor
import com.example.loginsignuppractice.widget.CustomButton

class SignUpPage {
    @Composable
    fun SignUpUi(navController: NavController) {
        BasicUi(
            true,
            navController,
            "Sign Up",
            "Sign up to discover amazing near around you.",
            ui = { SignUpContent() },
            backAction = {
                navController.popBackStack()
            })
    }

    @Composable
    fun SignUpContent() {
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
        CustomButton(text = "Sign Up") {

        }
        Spacer(modifier = Modifier.height(20.dp))
        Row() {
            Text(
                "By signing up you accpet out ",
                style = TextStyle(
                    fontSize = 10.sp
                )
            )
            ClickableText(
                text = AnnotatedString(
                    "Terms of Services ",
                    spanStyle = SpanStyle(
                        fontSize = 10.sp,
                        color = mainColor
                    )
                ),
                onClick = {

                })
            Text(
                "and",
                style = TextStyle(
                    fontSize = 10.sp
                )
            )

        }
        ClickableText(
            text = AnnotatedString(
                "Private Policy",
                spanStyle = SpanStyle(
                    fontSize = 10.sp,
                    color = mainColor
                )
            ),
            onClick = {

            })
    }


}
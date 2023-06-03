package com.example.loginsignuppractice.page

import BasicUi
import IconTextField
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.loginsignuppractice.R
import com.example.loginsignuppractice.Route
import com.example.loginsignuppractice.component.CustomButton
import com.example.loginsignuppractice.repository.UserRepository
import com.example.loginsignuppractice.util.imageMap


@Composable
fun registerUserInfoUi(userRepository: UserRepository, navController: NavController) {
    BasicUi(
        false,
        navController,
        "Register your Information",
        "Please enter your nickname and select your icon",
        ui = { registerUserInfoContent(userRepository, navController) },
        backAction = {
            navController.popBackStack()
        })
}


@Composable
fun registerUserInfoContent(userRepository: UserRepository, navController: NavController) {
    var nickname by remember { mutableStateOf(TextFieldValue("")) }
    var profileNum by remember { mutableStateOf(1) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(
            onClick = {
                profileNum = if (profileNum - 1 == 0) 4
                else profileNum - 1
                Log.d("profileNum", profileNum.toString())
            }
        ) {
            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "")

        }
        Image(
            painter = painterResource(imageMap[profileNum] ?: R.drawable.rabbit),
            contentDescription = "",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)                       // clip to the circle shape
                .border(2.dp, Color.Transparent, CircleShape)
        )
        IconButton(
            onClick = {
                profileNum = if (profileNum + 1 == 5) 1
                else profileNum + 1
                Log.d("profileNum", profileNum.toString())

            }) {
            Icon(imageVector = Icons.Outlined.ArrowForward, contentDescription = "")

        }
    }

    Spacer(modifier = Modifier.height(30.dp))


    IconTextField(
        imageVector = Icons.Default.Person,
        contentType = "Nickname",
        textFieldValue = nickname
    )

    Spacer(modifier = Modifier.height(20.dp))
    CustomButton(text = "Register nickname") {
        userRepository.updateUserInfo("nickname", nickname)
        navController.navigate(
            Route.Main.routes,
            NavOptions.Builder()
                .setPopUpTo(Route.Main.routes, true)
                .build()
        )    }
}

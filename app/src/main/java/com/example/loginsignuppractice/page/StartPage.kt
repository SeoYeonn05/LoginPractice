package com.example.loginsignuppractice.page

import Background
import ConnectIcon
import ConnectLine
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginsignuppractice.Route
import com.example.loginsignuppractice.component.CustomButton
import com.example.loginsignuppractice.repository.AuthRepository
import com.example.loginsignuppractice.util.SharedPreferenceUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class StartPage(authRepository: AuthRepository, navController: NavController) :
    ComponentActivity() {
    private var navController = navController
    private var authRepository = authRepository
    var nickname: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var context: Context = this
        var pref: SharedPreferenceUtil? = SharedPreferenceUtil()
        val currentUser = authRepository.getCurrentUser()

        if (pref != null) {
            nickname = pref!!.getString(context, "nickname", "")
        }


        //&&this?.getBoolean("AutoLoginChecked", false) == true
        if (currentUser != null && nickname == "") {
            navController.navigate(Route.RegisterUserInfo.routes)
        } else if (currentUser != null && nickname != "") {
            navController.navigate(Route.Main.routes)
        }


    }


    @SuppressLint("NotConstructor")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StartUI() {
        Box() {
            Background(height = 400)
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = "Logo",
                    modifier = Modifier.size(128.dp)
                )
                Text(
                    "A P P  I C O N",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Spacer(modifier = Modifier.height(30.dp))

                Card(
                    colors = CardDefaults.cardColors(
                        Color.White
                    ),
                    modifier = Modifier
                        .height(380.dp)
                        .width(340.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.width(280.dp)
                        ) {
                            Text(
                                "Welcome to App Name.",
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                "Discover Amazing Thing Near Around You.",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Light
                                )
                            )
                            Spacer(modifier = Modifier.height(30.dp))
                            CustomButton("Sign In") {
                                navController.navigate(Route.SignIn.routes)
                            }
                            OutlinedButton(
                                onClick = {
                                    navController.navigate(Route.SignUp.routes)

                                },
                                modifier = Modifier
                                    .wrapContentSize()
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(5.dp),
                            ) {
                                Text("Sign Up")
                            }
                            Spacer(modifier = Modifier.height(30.dp))
                            ConnectLine()
                            Spacer(modifier = Modifier.height(20.dp))
                            ConnectIcon(navController)

                        }
                    }
                }
                Spacer(modifier = Modifier.height(50.dp))
                TextButton(
                    onClick = {},
                ) {
                    Text(
                        "SKIP", style = TextStyle(
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }

}

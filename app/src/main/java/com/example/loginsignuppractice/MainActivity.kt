package com.example.loginsignuppractice

import Background
import ConnectIcon
import ConnectLine
import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginsignuppractice.component.CustomButton
import com.example.loginsignuppractice.ui.theme.LoginSignUpPracticeTheme
import com.example.loginsignuppractice.ui.theme.Purple40
import com.example.loginsignuppractice.ui.theme.backgroundColor
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.util.*

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginSignUpPracticeTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = backgroundColor
                ) {
                    StartPage()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartPage() {
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
                ){
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
                        CustomButton("Sign In")
                        OutlinedButton(
                            onClick = {},
                            modifier = Modifier
                                .wrapContentSize()
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Sign Up")
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        ConnectLine()
                        Spacer(modifier = Modifier.height(30.dp))
                        ConnectIcon()

                    }
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                "SKIP",
                color = Color.DarkGray,
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginSignUpPracticeTheme {
    }
}
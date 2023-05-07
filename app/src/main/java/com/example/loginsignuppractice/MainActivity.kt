package com.example.loginsignuppractice

import Background
import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.GenericShape
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
        Background(height = 300)
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
                "APP ICON",
                style = TextStyle(
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(30.dp))

            Card(
                colors = CardDefaults.cardColors(
                    Color.White
                ),
                modifier = Modifier
                    .height(380.dp)
                    .width(300.dp),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
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
                        onClick = {}) {
                        Text("Sign Up")
                    }

                }
            }
            Text(
                "SKIP",
                color = Color.DarkGray
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicBackground() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "aa")
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Unspecified.copy(alpha = 0f)),
            )
        }
    ) {

    }
}


@Composable
fun MyDialog(modifier: Modifier) {
    Column() {

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginSignUpPracticeTheme {
    }
}
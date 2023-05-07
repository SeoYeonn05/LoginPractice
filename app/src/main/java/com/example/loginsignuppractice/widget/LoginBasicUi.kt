import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginsignuppractice.ui.theme.backgroundColor
import com.example.loginsignuppractice.widget.CustomButton

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicUi(title: String, content: String, ui: @Composable ()-> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Icon(imageVector = Icons.Default.Face, contentDescription = "Logo") },
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) {
        Box(
            modifier = Modifier
                .background(color = backgroundColor)
                .fillMaxSize()
        ) {
            Background(height = 400)
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        "$title",
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 25.sp,
                        )
                    )
                    Text(
                        "$content",
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Light,
                            fontSize = 15.sp,
                        )
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Card(
                    colors = CardDefaults.cardColors(
                        Color.White
                    ),
                    modifier = Modifier
                        .height(350.dp)
                        .fillMaxWidth(),
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
/*                            CustomButton("Sign In")
                            OutlinedButton(
                                onClick = {},
                                modifier = Modifier
                                    .wrapContentSize()
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text("Sign Up")
                            }*/

                        }
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                ConnectLine()
                Spacer(modifier = Modifier.height(20.dp))
                Card(
                    modifier = Modifier
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(25.dp)
                        )
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        Color.White
                    )) {
                    ConnectIcon()

                }
                Spacer(modifier = Modifier.height(40.dp))
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
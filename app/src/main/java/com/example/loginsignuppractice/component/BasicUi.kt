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
import androidx.navigation.NavController
import com.example.loginsignuppractice.Route
import com.example.loginsignuppractice.ui.theme.backgroundColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicUi(
    loginPage: Boolean,
    navController: NavController,
    title: String,
    content: String,
    ui: @Composable () -> Unit,
    backAction: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
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
                MyAppBar(title, content, navController)
                Spacer(modifier = Modifier.height(20.dp))
                // 동적으로 카드 크기 조정은 어떻게 하는걸까?
                Card(
                    colors = CardDefaults.cardColors(
                        Color.White
                    ),
                    modifier = Modifier
                        .height(350.dp)
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min),
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
                            ui()
                        }
                    }
                }
                if(loginPage){
                    SocialLoginButton(navController)
                } else{
                    Spacer(modifier = Modifier.height(175.dp))

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SocialLoginButton(navController: NavController,){
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
        )
    ) {
        ConnectIcon(navController)

    }
    Spacer(modifier = Modifier.height(30.dp))
    TextButton(
        onClick = {
        },
    ) {
        Text(
            "SKIP", style = TextStyle(
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold
            )
        )
    }
}
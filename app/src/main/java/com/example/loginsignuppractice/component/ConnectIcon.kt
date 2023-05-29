import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginsignuppractice.Route
import com.example.loginsignuppractice.ui.theme.backgroundColor

@Composable
fun ConnectLine() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier.width(95.dp),
            color = Color.DarkGray, thickness = 1.dp
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            "Or connect using",

            style = TextStyle(
                color = Color.DarkGray,
                fontSize = 10.sp
            )
        )
        Spacer(modifier = Modifier.width(10.dp))
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Color.DarkGray, thickness = 1.dp
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectIcon(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp, start = 30.dp, end = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = Color(0xFF2C71AD),
                    shape = RoundedCornerShape(10.dp)
                )
                .height(40.dp)
                .width(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "f",
                style = TextStyle(
                    color = Color.White
                )
            )
        }
        Box(
            modifier = Modifier
                .background(
                    color = Color(0xFF00CDF7),
                    shape = RoundedCornerShape(10.dp)
                )
                .height(40.dp)
                .width(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "t",
                style = TextStyle(
                    color = Color.White
                )
            )
        }
        Box(
            modifier = Modifier
                .background(
                    color = Color(0xFFFF4D4D),
                    shape = RoundedCornerShape(10.dp)
                )
                .height(40.dp)
                .width(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "g",
                style = TextStyle(
                    color = Color.White
                )
            )
        }
        Box(
            modifier = Modifier
                .background(
                    color = Color(0xFF686868),
                    shape = RoundedCornerShape(10.dp)
                )
                .height(40.dp)
                .width(40.dp),
            contentAlignment = Alignment.Center,

            ) {
            IconButton(
                onClick = {
                    navController.navigate(Route.NumberLogin.routes)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = "phone",
                    tint = Color.White
                )
            }
        }
    }
}



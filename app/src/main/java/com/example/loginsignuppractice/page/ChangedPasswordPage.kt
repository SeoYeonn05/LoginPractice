import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginsignuppractice.Route
import com.example.loginsignuppractice.ui.theme.mainColor
import com.example.loginsignuppractice.component.CustomButton


@Composable
fun ChangedPasswordUi(navController: NavController) {
    BasicUi(
        false,
        navController,
        "",
        "",
        ui = { ChangedPasswordContent(navController) },
        backAction = {
            navController.popBackStack()
        })
}

@Composable
fun ChangedPasswordContent(navController: NavController) {

    Text(
        "Password Changed",
        style = TextStyle(
            color = mainColor,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 25.sp,
        )
    )
    Text(
        "Congratuation!! You've successfullly",
        style = TextStyle(
            color = Color.DarkGray,
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
        )
    )
    Text(
        "changed your password.",
        style = TextStyle(
            color = Color.DarkGray,
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
        )
    )
    Spacer(modifier = Modifier.height(20.dp))
    Icon(
        imageVector = Icons.Default.ThumbUp, contentDescription = "ChangedPassword",
        modifier = Modifier
            .size(80.dp),
        tint = mainColor
        )
    Spacer(modifier = Modifier.height(30.dp))
    CustomButton(text = "Back to Login") {
        //navController.pop
        navController.navigate(
            route = Route.Start.routes,
            builder = {
                popUpTo(Route.EnterOTP.routes) {
                    inclusive = true
                }
            }
        )
    }
}

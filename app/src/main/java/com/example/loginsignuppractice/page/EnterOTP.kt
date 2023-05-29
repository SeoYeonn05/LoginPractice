import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginsignuppractice.Route
import com.example.loginsignuppractice.ui.theme.mainColor
import com.example.loginsignuppractice.component.CustomButton

@Composable
fun EnterOTP(navController: NavController, phoneNum: String) {
    BasicUi(
        false,
        navController,
        "Enter OTP",
        "Please enter 6 digit Code sent to your registered mobile number $phoneNum",
        ui = { EnterOTPContent(navController) },
        backAction = {
            navController.popBackStack()
        })
}


@Composable
fun EnterOTPContent(navController: NavController) {

    DuckieTextField()
    Spacer(modifier = Modifier.height(20.dp))
    CustomButton(text = "Submit") {
        navController.navigate(Route.NewPassword.routes)
    }
    Spacer(modifier = Modifier.height(20.dp))
    Row() {
        Text(
            "Not received Code? ",
            style = TextStyle(
                fontSize = 10.sp
            )
        )
        ClickableText(
            text = AnnotatedString(
                "Resend",
                spanStyle = SpanStyle(
                    fontSize = 10.sp,
                    color = mainColor
                )
            ),
            onClick = {

            })
    }
}

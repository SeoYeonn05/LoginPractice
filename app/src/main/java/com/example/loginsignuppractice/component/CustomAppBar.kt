import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginsignuppractice.ui.theme.mainColor


@Composable
fun MyAppBar(title: String, content: String, navController: NavController){
    Column(
        modifier = Modifier.fillMaxWidth()
            .height(130.dp),
        horizontalAlignment = Alignment.Start
    ){
        Row(
            modifier = Modifier
                .background(
                    color = mainColor,
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ){
            IconButton(
                onClick = {navController.popBackStack()
                }
            ) {
                Icon(

                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Spacer(modifier = Modifier.width(100.dp))
            Icon(
                imageVector = Icons.Default.Face, contentDescription = "Logo",
                modifier = Modifier
                    .size(50.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

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
    }

}
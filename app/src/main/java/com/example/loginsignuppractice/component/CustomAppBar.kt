import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.example.loginsignuppractice.ui.theme.mainColor
import java.lang.reflect.Modifier

@Composable
fun MyAppBar(){
    Row(
        modifier = androidx.compose.ui.Modifier
            .background(
                color = mainColor,
            )
    ){
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = "Logo"
            )
        }
    }
}
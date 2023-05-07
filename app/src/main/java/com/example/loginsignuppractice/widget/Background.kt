import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.loginsignuppractice.ui.theme.mainColor
import kotlinx.coroutines.selects.select

@Composable
fun Background(height: Int) {
    val density = LocalDensity.current
    val backgroundBottomPadding = 96.dp
    var boxHeight by remember { mutableStateOf(264.dp)}

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
            .background(
                color = mainColor,
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomEnd = 23.dp,
                    bottomStart = 23.dp
                )
            )
            .onSizeChanged {
                boxHeight = with(density) { it.height.toDp() }
            }
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(backgroundBottomPadding))
    }

}





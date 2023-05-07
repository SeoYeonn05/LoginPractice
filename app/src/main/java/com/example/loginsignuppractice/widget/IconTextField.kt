import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun IconTextField(imageVector: ImageVector, contentType: String, textFieldValue: TextFieldValue) {
    var text by remember { mutableStateOf(textFieldValue) }
    TextField(
        value = text,
        leadingIcon = { Icon(imageVector = imageVector, contentDescription = "") },
        placeholder = { Text(text = contentType) },
        onValueChange = { text = it },
        colors = TextFieldDefaults.outlinedTextFieldColors(
/*                            focusedBorderColor = Color.Blue,
                            focusedLabelColor = Color.Yellow,*/
            backgroundColor = Color.Transparent,
        ),
    )
}
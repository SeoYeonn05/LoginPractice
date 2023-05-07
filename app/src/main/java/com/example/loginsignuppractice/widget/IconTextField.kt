import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.loginsignuppractice.ui.theme.backgroundColor
import java.util.regex.Pattern

@Composable
fun IconTextField(imageVector: ImageVector, contentType: String, textFieldValue: TextFieldValue) {
    var text by remember { mutableStateOf(textFieldValue) }
    TextField(
        value = text,
        leadingIcon = { Icon(imageVector = imageVector, contentDescription = "") },
        placeholder = { Text(text = contentType) },
        onValueChange = { text = it },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = backgroundColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
    )
}

@Composable
fun EmailTextField(textFieldValue: TextFieldValue){
    val infoIconView = @Composable {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "",
            tint = Color.LightGray,
        )
    }
    var email by remember { mutableStateOf(textFieldValue) }

    TextField(
        value = email,
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "") },
        placeholder = { Text(text = "Email") },
        onValueChange = { email = it },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = backgroundColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        trailingIcon = if (!checkEmail(email.text) && email.text.isNotBlank()) infoIconView else null
    )
}

fun checkEmail(text: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(text).matches()
}

@Composable
fun PasswordTextField(textFieldValue: TextFieldValue){
    var password by remember { mutableStateOf(textFieldValue) }

    val infoIconView = @Composable {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "",
            tint = Color.LightGray,
        )
    }

    TextField(
        value = password,
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "") },
        placeholder = { Text(text = "Password") },
        onValueChange = { password = it },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = backgroundColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        visualTransformation = PasswordVisualTransformation(),
        trailingIcon = if (!checkPassword(password.text) && password.text.isNotBlank()) infoIconView else null
    )
}

fun checkPassword(text: String): Boolean {
    // 8~20자리 영어, 숫자, 특수문자 포함
    return Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", text)
}


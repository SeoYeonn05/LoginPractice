import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.*
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

@Composable
fun DuckieTextField() {
    var text by remember { mutableStateOf("") }

    BasicTextField(
        value = text,
        onValueChange = {text = it},
        decorationBox = {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                text.forEachIndexed { index, char ->
                    DuckieTextFieldCharContainer(
                        text = char,
                        isFocused = index == text.lastIndex,
                    )
                }
                repeat(6 - text.length) {
                    DuckieTextFieldCharContainer(
                        text = ' ',
                        isFocused = false,
                    )
                }
            }
        },
    )
}

@Composable
fun PhoneNumField(
    mask: String = "000 0000 0000",
    maskNumber: Char = '0',
) {
    var phoneNum by remember { mutableStateOf("") }
    val infoIconView = @Composable {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "",
            tint = Color.LightGray,
        )
    }
    TextField(
        value = phoneNum,
        onValueChange = { it ->
            phoneNum = it
            it.take(mask.count { it == maskNumber })
        },
        label = {
            Text(text = "Enter mobile number")
        },
        leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = "") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        visualTransformation = PhoneVisualTransformation(mask, maskNumber),
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = backgroundColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
    )
}

/*fun checkPhoneNum(num: TextFieldValue): Boolean {

}*/
class PhoneVisualTransformation(val mask: String, val maskNumber: Char) : VisualTransformation {

    private val maxLength = mask.count { it == maskNumber }

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.length > maxLength) text.take(maxLength) else text

        val annotatedString = buildAnnotatedString {
            if (trimmed.isEmpty()) return@buildAnnotatedString

            var maskIndex = 0
            var textIndex = 0
            while (textIndex < trimmed.length && maskIndex < mask.length) {
                if (mask[maskIndex] != maskNumber) {
                    val nextDigitIndex = mask.indexOf(maskNumber, maskIndex)
                    append(mask.substring(maskIndex, nextDigitIndex))
                    maskIndex = nextDigitIndex
                }
                append(trimmed[textIndex++])
                maskIndex++
            }
        }

        return TransformedText(annotatedString, PhoneOffsetMapper(mask, maskNumber))
    }
}

private class PhoneOffsetMapper(val mask: String, val numberChar: Char) : OffsetMapping {

    override fun originalToTransformed(offset: Int): Int {
        var noneDigitCount = 0
        var i = 0
        while (i < offset + noneDigitCount) {
            if (mask[i++] != numberChar) noneDigitCount++
        }
        return offset + noneDigitCount
    }

    override fun transformedToOriginal(offset: Int): Int =
        offset - mask.take(offset).count { it != numberChar }
}
@Composable
private fun DuckieTextFieldCharContainer(
    modifier: Modifier = Modifier,
    text: Char,
    isFocused: Boolean,
) {
    val shape = remember { RoundedCornerShape(4.dp) }

    Box(
        modifier = modifier
            .size(
                width = 40.dp,
                height = 46.dp,
            )
            .background(
                color = backgroundColor,
                shape = shape,
            )
            .run {
                if (isFocused) {
                    border(
                        width = 3.dp,
                        color = Color(0xFFFF8300),
                        shape = shape,
                    )
                } else {
                    this
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        Text(text = text.toString())
    }
}
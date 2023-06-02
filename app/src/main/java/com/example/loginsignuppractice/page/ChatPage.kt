package com.example.loginsignuppractice.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginsignuppractice.R
import java.util.*

class ChatPage {

    @Composable
    fun receiver(
        text: String, name: String, time: Date, profile: Int
    ) {

        return Column(
            modifier = Modifier.padding(10.dp),
        ) {
            Row(
                verticalAlignment = Alignment.Top
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,            // crop the image if it's not a square
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)                       // clip to the circle shape
                        .border(2.dp, Color.Gray, CircleShape)   // add a border (optional)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Column(
                    //modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start,

                    ) {
                    Text(
                        text = name,
                        modifier = Modifier.padding(12.dp, 0.dp, 0.dp, 0.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .clip(shape = RoundedCornerShape(9.dp))
                                .background(color = Color.White)
                                .widthIn(0.dp, 240.dp),
                        ) {
                            Text(
                                text = text,
                                style = TextStyle(fontSize = 18.sp),
                                modifier = Modifier.padding(6.dp),
                                softWrap = true
                            )
                        }
                        Text(
                            text = time.time.toString(),
                            style = TextStyle(fontSize = 12.sp, color = Color.Black),
                            modifier = Modifier.padding(8.dp),
                        )
                    }
                }
                Spacer(modifier = Modifier.width(5.dp))

            }
        }
    }

}
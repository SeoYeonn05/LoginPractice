package com.example.loginsignuppractice.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginsignuppractice.R
import com.example.loginsignuppractice.chatList
import com.example.loginsignuppractice.model.ChatMessage
import com.example.loginsignuppractice.repository.ChatRepository
import com.example.loginsignuppractice.viewModel.ChatViewModel
import com.google.type.Date
import com.google.type.DateTime
import java.text.SimpleDateFormat
import java.time.LocalDateTime

class ChatListPage {
    var chatViewModel: ChatViewModel = ChatViewModel()

    @Composable
    fun chatRoomList(list: List<ChatMessage>) {
        LazyColumn(){
            items(list){ item: ChatMessage ->  
                chatRoom("",item)
            }
        }
    }

    @Composable
    fun chatRoom(name: String, chatMessage: ChatMessage) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically

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
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier.weight(10f)
            ) {
                androidx.compose.material3.Text(
                    text = name,
                    style = TextStyle(fontSize = 16.sp),
                    fontWeight = FontWeight.Bold,

                    //modifier = Modifier.padding(12.dp, 0.dp, 0.dp, 0.dp)
                )
                androidx.compose.material3.Text(
                    text = name,
                    style = TextStyle(fontSize = 12.sp, color = Color.DarkGray),
                    modifier = Modifier.padding(6.dp),
                    softWrap = true
                )
            }
            Column(modifier = Modifier.weight(3f)) {
                androidx.compose.material3.Text(
                    text = chatMessage.sendAt,
                    style = TextStyle(fontSize = 11.sp, color = Color.DarkGray),
                    //modifier = Modifier.padding(12.dp, 0.dp, 0.dp, 0.dp)
                )
                if (true) {
                    null
                } else {
                    androidx.compose.material3.Text(
                        text = chatMessage.toString(),
                        style = TextStyle(fontSize = 11.sp, color = Color.White),
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(8.dp)
                            .drawBehind {
                                drawCircle(
                                    color = Color.Red,
                                    radius = this.size.maxDimension / 1.7f
                                )
                            },
                        overflow = TextOverflow.Ellipsis
                    )
                }

            }

        }
    }
}
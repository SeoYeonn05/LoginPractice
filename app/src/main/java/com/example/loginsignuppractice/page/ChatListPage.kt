package com.example.loginsignuppractice.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import com.example.loginsignuppractice.model.Chat
import com.example.loginsignuppractice.model.ChatMessage
import com.example.loginsignuppractice.repository.FireStoreRepository
import com.example.loginsignuppractice.viewModel.ChatViewModel


class ChatListPage(chatViewModel: ChatViewModel) {
    var chatViewModel = chatViewModel

    @Composable
    fun chatRoomList() {


        LazyColumn() {
            items(chatViewModel.getChatRoomList()) { item: Chat ->
                /*item.
                chatRoom(, item, 1)*/
            }
        }
    }

    @Composable
    fun chatRoom(nickname: String, lastMessage: ChatMessage, messageNum: Int) {
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
                Text(
                    text = nickname,
                    style = TextStyle(fontSize = 16.sp),
                    fontWeight = FontWeight.Bold,

                    //modifier = Modifier.padding(12.dp, 0.dp, 0.dp, 0.dp)
                )
                Text(
                    text = lastMessage.messageText,
                    style = TextStyle(fontSize = 12.sp, color = Color.DarkGray),
                    modifier = Modifier.padding(6.dp),
                    softWrap = true
                )
            }
            Column(modifier = Modifier.weight(3f)) {
                Text(
                    text = lastMessage.sendAt.toString(),
                    style = TextStyle(fontSize = 11.sp, color = Color.DarkGray),

                    )
                if (true) {
                    null
                } else {
                    Text(
                        text = messageNum.toString(),
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
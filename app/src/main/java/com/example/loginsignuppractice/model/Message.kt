package com.example.loginsignuppractice.model

import java.sql.Timestamp

data class Chat(
    val messageList: List<ChatMessage>,
    val receiver: String,
    val sender: String
)

data class ChatMessage(
    var isRead: Boolean,
    var messageText: String,
    var sendAt: Timestamp,
    var sendBy: String
)

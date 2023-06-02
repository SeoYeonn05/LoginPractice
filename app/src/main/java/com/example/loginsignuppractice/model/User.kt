package com.example.loginsignuppractice.model

data class UserInfo(
    var uId: String,
    var friendsList: MutableList<String>? = null,
    var chatRoomList: MutableList<String>? = null,
    var profileImage: Int
)

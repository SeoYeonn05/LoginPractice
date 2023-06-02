package com.example.loginsignuppractice.model

data class UserInfo(
    var friendsList: MutableList<String>? = null,
    var nickname: String,
    var profileImage: Int
)

package com.example.loginsignuppractice.repository

import android.content.Context
import android.util.Log
import com.example.loginsignuppractice.model.Friend
import com.example.loginsignuppractice.model.UserInfo
import com.example.loginsignuppractice.util.SharedPreferenceUtil
import com.google.firebase.firestore.FirebaseFirestore


class UserRepository(context: Context) {
    private val db = FirebaseFirestore.getInstance()
    private var userAutoId = ""

    //var userAutoId = "users"

    init {
        //userAutoId = db.collection("user").document().id
        userAutoId = SharedPreferenceUtil().getString(context, "uid", "")
        Log.d("sharedPreference userAutoId:", userAutoId)

    }

    fun createUser() {
        Log.d("createUser userAutoId:", userAutoId)

        val userInfo: UserInfo? = UserInfo(
            "",
            mutableListOf(""),
            mutableListOf(""),
            1
        )
        val user = mapOf(userAutoId!! to userInfo!!)

        db.collection("user").document(userAutoId).set(user)
        Log.d("createUser userInfo:", user.toString())
    }

    fun getUserInfo(userAutoId: String): UserInfo {
        Log.d("autoId:", userAutoId)

        var userRef = db.collection("user").document(userAutoId)
        var userInfo = UserInfo(userAutoId, mutableListOf(), mutableListOf(), 1)
        userRef.get().addOnSuccessListener { userDoc ->
            var firebaseData = userDoc.data?.get(userAutoId) as? Map<String, Any>?
            val friendsList = firebaseData?.get("friendsList") as? MutableList<String>
            val chatRoomList = firebaseData?.get("chatRoomList") as? MutableList<String>
            val nickname = firebaseData?.get("nickname") as? String
            val profileImage = firebaseData?.get("profileImage") as? Int

            userInfo = UserInfo(
                friendsList = friendsList,
                chatRoomList = chatRoomList,
                nickname = nickname ?: "",
                profileImage = profileImage ?: 0
            )
            Log.d("Success userInfo:", userDoc.data.toString())
        }

        return userInfo
    }

    fun getFriendsList(userAutoId: String):List<Friend>{
        var userRef = db.collection("user").document(userAutoId)
        var userInfo = getUserInfo(userAutoId)
        var friendsList = mutableListOf<Friend>()

        for (friendId in userInfo.friendsList!!){
            var friend = getUserInfo(friendId)
            var friendInfo = Friend(
                friendId,
                friend.nickname,
                friend.profileImage
            )
            friendsList.add(friendInfo)
        }

        return friendsList
    }

    fun updateUserInfo(key: String, data: Any) {
        Log.d("updateUserInfo autoId:", userAutoId)
        var userRef = db.collection("user").document(userAutoId)

        var userInfo = getUserInfo(userAutoId)

        when(key){
            "nickname" -> {
                val data = data as String

                userInfo = UserInfo(
                    data,
                    userInfo.friendsList,
                    userInfo.chatRoomList,
                    userInfo.profileImage
                )
            }
            "chatRoomList" -> {
                val chatRoomList = userInfo.chatRoomList ?: mutableListOf()
                val data = data as String

                if (chatRoomList.contains(data)) {
                    Log.d("Friend already exists in the chatRoomList:", data)
                    return
                }

                chatRoomList.add(data)

                userInfo = UserInfo(
                    userInfo.nickname,
                    userInfo.friendsList,
                    chatRoomList,
                    userInfo.profileImage
                )
            }
            "friendsList" -> {
                val friendsList = userInfo.friendsList ?: mutableListOf()
                val data = data as String

                Log.d("userInfo:", friendsList.toString())

                if (friendsList.contains(data)) {
                    Log.d("Friend already exists in the friendList:", data)
                    return
                }

                friendsList.add(data)

                userInfo = UserInfo(
                    userInfo.nickname,
                    friendsList,
                    userInfo.chatRoomList,
                    userInfo.profileImage
                )
            }
            "profileImage" -> {

                userInfo = UserInfo(
                    userInfo.nickname,
                    userInfo.friendsList,
                    userInfo.chatRoomList,
                    data as Int
                )
            }

        }

        val userInfoData = mapOf(
            userAutoId!! to userInfo!!
        )
        Log.d("updateFriendsList userInfo:", userInfoData.toString())


        userRef.update(userInfoData as Map<String, Any>)
            .addOnSuccessListener {
                println("FriendList updated successfully.")
            }
            .addOnFailureListener { error ->
                println("Error updating friendList: $error")
            }
    }

    fun updateChatRoomList(nickname: String, chatRoomId: String) {
        Log.d("autoId:", userAutoId)
        var userRef = db.collection("user").document(userAutoId)

        var userInfo = getUserInfo(nickname)
        val chatRoomList = userInfo.chatRoomList ?: mutableListOf()

        Log.d("userInfo:", chatRoomList.toString())

        if (chatRoomList.contains(chatRoomId)) {
            Log.d("Friend already exists in the friendList:", chatRoomId)
            return
        }

        chatRoomList.add(chatRoomId)

        userInfo = UserInfo(
            userAutoId,
            userInfo.friendsList,
            chatRoomList,
            userInfo.profileImage
        )

        val userInfoData = mapOf(
            nickname!! to userInfo!!
        )
        Log.d("updateChatRoomList userInfo:", userInfoData.toString())


        userRef.update(userInfoData as Map<String, Any>)
            .addOnSuccessListener {
                println("ChatRoomList updated successfully.")
            }
            .addOnFailureListener { error ->
                println("Error updating ChatRoomList: $error")
            }
    }

}
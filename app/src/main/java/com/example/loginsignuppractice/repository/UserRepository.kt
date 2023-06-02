package com.example.loginsignuppractice.repository

import android.util.Log
import com.example.loginsignuppractice.model.UserInfo
import com.google.firebase.firestore.FirebaseFirestore


class UserRepository {
    private val db = FirebaseFirestore.getInstance()
    private var userAutoId = ""

    //private val userId = AuthRepository().getCurrentUser()
    //var userAutoId = "users"

    init {
        userAutoId = db.collection("user").document().id
    }

    fun createUser(nickname: String) {
        val userInfo: UserInfo? = UserInfo(
            userAutoId,
            mutableListOf(""),
            mutableListOf(""),
            1
        )
        val user = mapOf(nickname!! to userInfo!!)

        db.collection("user").document(userAutoId).set(user)
        Log.d("createUser userInfo:", user.toString())
    }

    fun getUserInfo(nickname: String): UserInfo {
        Log.d("autoId:", userAutoId)

        var userRef = db.collection("user").document(userAutoId)
        var userInfo = UserInfo(userAutoId, mutableListOf(), mutableListOf(), 1)
        userRef.get().addOnSuccessListener { userDoc ->
            var firebaseData = userDoc.data?.get(nickname) as? Map<String, Any>?
            val friendsList = firebaseData?.get("friendsList") as? MutableList<String>
            val chatRoomList = firebaseData?.get("chatRoomList") as? MutableList<String>
            val uId = firebaseData?.get("uid") as? String
            val profileImage = firebaseData?.get("profileImage") as? Int

            userInfo = UserInfo(
                friendsList = friendsList,
                chatRoomList = chatRoomList,
                uId = uId ?: "",
                profileImage = profileImage ?: 0
            )
            Log.d("Success userInfo:", userDoc.data.toString())
        }

        Log.d("userInfo:", userInfo.friendsList.toString())

        return userInfo
    }

    fun updateFriendsList(nickname: String, friendId: String) {
        Log.d("autoId:", userAutoId)
        var userRef = db.collection("user").document(userAutoId)

        var userInfo = getUserInfo(nickname)
        val friendsList = userInfo.friendsList ?: mutableListOf()

        Log.d("userInfo:", friendsList.toString())

        if (friendsList.contains(friendId)) {
            Log.d("Friend already exists in the friendList:", friendId)
            return
        }

        friendsList.add(friendId)

        userInfo = UserInfo(
            userAutoId,
            friendsList,
            userInfo.chatRoomList,
            userInfo.profileImage
        )

        val userInfoData = mapOf(
            nickname!! to userInfo!!
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
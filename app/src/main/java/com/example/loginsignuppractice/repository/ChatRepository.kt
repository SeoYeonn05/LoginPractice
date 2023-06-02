package com.example.loginsignuppractice.repository

import android.util.Log
import com.example.loginsignuppractice.model.UserInfo
import com.google.firebase.firestore.FirebaseFirestore


class ChatRepository {
    private val db = FirebaseFirestore.getInstance()
    private val userId = "seoyeon"

    //private val userId = AuthRepository().getCurrentUser()
    var userAutoId = db.collection("user").document().id
    //var userAutoId = "users"

    fun createUser() {
        val userInfo: UserInfo? = UserInfo(
            mutableListOf(""),
            "6월 1일 오후 03:22",
            1
        )
        val user = mapOf(userId!! to userInfo!!)

        db.collection("user").document(userAutoId).set(user)
    }

    fun getUserInfo(userId: String): UserInfo {
        var userRef = db.collection("user").document("XSVtNpI5c7cUBtul1R7w")
        var userInfo = UserInfo(mutableListOf(), "", 1)
        userRef.get().addOnSuccessListener { userDoc ->
            var firebaseData = userDoc.data?.get("") as? Map<String, Any>?
            val friendsList = firebaseData?.get("friendsList") as? MutableList<String>
            val nickname = firebaseData?.get("nickname") as? String
            val profileImage = firebaseData?.get("profileImage") as? Int

            var userInfo = UserInfo(
                friendsList = friendsList,
                nickname = nickname ?: "",
                profileImage = profileImage ?: 0
            )
            Log.d("Success userInfo:", userDoc.data.toString())

        }

        Log.d("userInfo:", userInfo.friendsList.toString())

        return userInfo
    }

    fun addFriend(userId: String, friendId: String) {
        Log.d("autoId:", userAutoId)
        var userRef = db.collection("user").document(userAutoId)

        userRef.get().addOnSuccessListener { userDoc ->
            val userInfo = getUserInfo("minsu")

            Log.d("userInfo:", userInfo.toString())

            val friendsList = userInfo.friendsList ?: mutableListOf()

            if (friendsList.contains(friendId)) {
                Log.d("Friend already exists in the friendList.:", friendId)
                return@addOnSuccessListener
            }

            friendsList.add(friendId)

            val data = hashMapOf(
                "friendsList" to friendsList
            )

            userRef.update(data as Map<String, Any>)
                .addOnSuccessListener {
                    println("FriendList updated successfully.")
                }
                .addOnFailureListener { error ->
                    println("Error updating friendList: $error")
                }
        }
            .addOnFailureListener { error ->
                Log.d("Cannot find userDoc", error.toString())
            }
    }

}
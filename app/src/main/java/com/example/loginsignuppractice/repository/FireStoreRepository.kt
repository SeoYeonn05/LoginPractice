package com.example.loginsignuppractice.repository

import android.util.Log
import com.example.loginsignuppractice.model.Chat
import com.example.loginsignuppractice.model.ChatMessage
import com.example.loginsignuppractice.model.Friend
import com.example.loginsignuppractice.model.UserInfo
import com.google.firebase.firestore.FirebaseFirestore
import java.sql.Timestamp


class FireStoreRepository(authRepository: AuthRepository) {
    private val db = FirebaseFirestore.getInstance()
    private var userAutoId = ""

    //var userAutoId = "users"

    init {
        userAutoId = authRepository.getCurrentUser()!!.uid
        Log.d("sharedPreference userAutoId:", userAutoId)

    }
    fun getUserAutoId():String{
        return userAutoId
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

    fun getFriendsList():List<Friend>{
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

    fun createChatRoom(receiverUid: String) {
        var chatRoomId = db.collection("message").document().id


        val chat: Chat? = Chat(
            messageList = mutableListOf<ChatMessage>(),
            receiver = getUserInfo(receiverUid).nickname,
            sender = ""
        )
        val message = mapOf(chatRoomId!! to chat!!)

        db.collection("message").document(chatRoomId).set(message)
        Log.d("createUser ChatRoom:", message.toString())
    }

    fun getChatRoomList(): List<Chat> {
        var chatRoomIdList = getUserInfo(userAutoId).chatRoomList
        var chatList = mutableListOf<Chat>()

        if (chatRoomIdList != null) {
            for(chatRoomId in chatRoomIdList){
                var chatRef = db.collection("message").document(chatRoomId)
                var chatMessageList = mutableListOf<ChatMessage>()
                chatRef.get().addOnSuccessListener { chatDoc ->
                    var firebaseData = chatDoc.data?.get(chatRoomId) as? Map<String, Any>?
                    val messageList = firebaseData?.get("message") as? MutableList<Map<String, Any>>
                    val receiver = firebaseData?.get("receiver") as? String
                    val sender = firebaseData?.get("sender") as? String

                    if (messageList != null) {
                        for(message in messageList){
                            val isRead = message?.get("isRead") as? Boolean
                            val messageText = message?.get("messageText") as? String
                            val sendAt = message?.get("sendAt") as? Timestamp
                            val sentBy = message?.get("sendAt") as? String
                            chatMessageList.add(
                                ChatMessage(
                                    isRead?:false,
                                    messageText?:"",
                                    (sendAt?:System.currentTimeMillis()) as Timestamp,
                                    sentBy?:"anonymous"
                                )
                            )
                        }
                    }
                    var chat = Chat(
                        chatMessageList,
                        receiver?:"",
                        sender?:""
                    )
                    chatList.add(chat)
                    Log.d("Success Chat:", chat.toString())
                }
            }
        }

        return chatList
    }
    fun sendMessage(chatRoomId: String){


    }

}
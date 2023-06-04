package com.example.loginsignuppractice.viewModel

import androidx.lifecycle.ViewModel
import com.example.loginsignuppractice.model.Chat
import com.example.loginsignuppractice.model.ChatMessage
import com.example.loginsignuppractice.repository.FireStoreRepository

class ChatViewModel(fireStoreRepository: FireStoreRepository,): ViewModel() {
var fireRepo = fireStoreRepository
    fun getChatRoomList():List<Chat>{
        return fireRepo.getChatRoomList()
    }

    fun getOtherNickname(){
        fireRepo.getUserAutoId()
    }

}
package com.example.loginsignuppractice.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginsignuppractice.model.Friend
import com.example.loginsignuppractice.repository.AuthRepository
import com.example.loginsignuppractice.repository.FireStoreRepository
import com.example.loginsignuppractice.ui.theme.mainColor
import com.example.loginsignuppractice.viewModel.ChatViewModel

class MainPage(fireStoreRepository: FireStoreRepository, authRepository: AuthRepository, navController: NavController) {
    var fireRepo = fireStoreRepository
    val navController = navController
    var chatViewModel = ChatViewModel(fireStoreRepository)

    @Composable
    fun TabScreen() {
        var tabIndex by remember { mutableStateOf(0) }

        val tabs = listOf("Friends", "Chatting")

        Column(modifier = Modifier.fillMaxWidth()) {
            TabRow(
                selectedTabIndex = tabIndex,
                contentColor = Color.White,
                backgroundColor = mainColor
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(text = {
                        Text(
                            text = title,
                            color = Color.White,
                            style = TextStyle(fontSize = 16.sp),
                        )
                    },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }
            when (tabIndex) {
                0 -> friendsListUi(
                    navController,
                    fireRepo,
                    fireRepo.getFriendsList()
                )
                1 -> ChatListPage(chatViewModel).chatRoomList()

            }
        }
    }
}
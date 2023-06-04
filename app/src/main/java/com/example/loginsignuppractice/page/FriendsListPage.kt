package com.example.loginsignuppractice.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginsignuppractice.R
import com.example.loginsignuppractice.Route
import com.example.loginsignuppractice.model.Friend
import com.example.loginsignuppractice.repository.FireStoreRepository
import com.example.loginsignuppractice.util.imageMap

@Composable
fun friendsListUi(navController: NavController, fireStoreRepository: FireStoreRepository, friendsList: List<Friend>) {
    LazyColumn(){
        items(friendsList){ item: Friend ->
            friendCard(navController, fireStoreRepository, item)
        }
    }
}

@Composable
fun friendCard(navController: NavController, fireStoreRepository: FireStoreRepository,friend: Friend){
    Row(
        modifier = Modifier.padding(14.dp),
        verticalAlignment = Alignment.CenterVertically

    ){
        Image(
            painter = imageMap.get(friend.profileNum)?.let { painterResource(it) }!!,
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape)
                .weight(1f)

        )
        Text(
            text = friend.nickname,
            style = TextStyle(fontSize = 19.sp),
            fontWeight = FontWeight.Bold,

            modifier = Modifier
                .padding(12.dp, 0.dp, 0.dp, 0.dp)
                .weight(7f)
        )
        IconButton(
            onClick = {
                fireStoreRepository.createChatRoom(friend.uid)
            },
            modifier = Modifier.weight(2f)

        ) {
            Icon(painter = painterResource(R.drawable.chat), contentDescription = "", modifier = Modifier.width(30.dp))
            //navController.navigate(Route..routes)
        }
    }
    Divider(thickness = 0.4.dp, color = Color.DarkGray)
}
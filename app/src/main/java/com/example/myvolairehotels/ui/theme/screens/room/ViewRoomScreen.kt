package com.example.myvolairehotels.ui.theme.screens.room
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.myvolairehotels.data.RoomViewModel
import com.example.myvolairehotels.models.Room
import com.example.myvolairehotels.navigation.ROUT_VIEWROOM


import com.example.myvolairehotels.ui.theme.screens.home.TopBar
import androidx.compose.runtime.remember as remember1

private fun ColumnScope.TextField(value: Nothing?, onValueChange: () -> Unit) {
    TODO("Not yet implemented")
}

class RoomItem(roomName: String, roomType: String, roomPrice: String, roomImageUrl: String, roomId: String, navController: NavHostController, roomRepository: RoomViewModel) {

}

@Composable
fun ViewRoomScreen(navController: NavHostController){
    Column(modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val context = LocalContext.current
        val roomRepository = RoomViewModel(navController, context)


        val emptyRoomsState = remember { mutableStateOf(Room.Rooms("", "", "", "", "")) }
        val emptyRoomsListState = remember { mutableStateListOf<Room.Rooms>() }

        val rooms = roomRepository.viewRooms(emptyRoomsState, emptyRoomsListState)


        Column(
            modifier = Modifier
                .fillMaxWidth(),

            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            TopBar()
            Text(text = "Rooms available;",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Black,
                textAlign = TextAlign.Left)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                items(rooms){
                    RoomItem(
                        roomName = "roomName",
                        roomType = "roomType",
                        roomPrice = "roomPrice",
                        roomImageUrl = "roomImageUrl",
                        roomId = "roomId",
                        navController = navController,
                        roomRepository = roomRepository

                    )
                }
            }
        }
    }

    class Room {

    }

    @Composable
    fun RoomItem(
        roomName:String,
        roomType:String,
        roomPrice:String,
        roomImageUrl:String,
        roomId:String,
        navController:NavHostController,
        roomRepository:RoomViewModel
    ) {
        Card(shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .size(400.dp, 400.dp)
                .padding(10.dp) ) {
            Column {
                Image(
                    painter = rememberAsyncImagePainter(roomImageUrl),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(400.dp,300.dp)
                )
                Row {
                    Column {
                        Text(text = roomName)
                        Text(text = roomType)
                        Text(text = roomPrice)
                    }
                    Spacer(modifier = Modifier.width(200.dp))
                    Button(onClick = {
                        navController.navigate(ROUT_VIEWROOM)
                    }) {
                        Text(text = "Book")
                    }
                }


            }
        }

    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopAppBarDefaults(){
        var displayMenu by remember1 { mutableStateOf(false) }
        val context = LocalContext.current.applicationContext
        TopAppBar(title = {Text(text = "Lux", fontFamily = FontFamily.Cursive, fontSize = 50.sp,
            fontWeight = FontWeight.Bold)},
            navigationIcon ={
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Home, contentDescription ="Home", tint = Color.Blue )

                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Cyan ,
                titleContentColor = Color.Black ,
                navigationIconContentColor = Color.White
            ),
            actions = {
                IconButton(onClick = { Toast.makeText(context,"You have clicked the search icon", Toast.LENGTH_SHORT).show()}) {
                    Icon(imageVector = Icons.Filled.Notifications, contentDescription = "notification", tint = Color.DarkGray)
                }
                IconButton(onClick = {Toast.makeText(context,"You can share using",Toast.LENGTH_SHORT).show()}) {
                    Icon(imageVector = Icons.Filled.Share, contentDescription = "Share")
                }
                IconButton(onClick = {displayMenu= displayMenu}) {
                    Icon(imageVector = Icons.Default.MoreVert,"menu")
                }
                DropdownMenu(expanded = displayMenu ,
                    onDismissRequest = {displayMenu = false}) {
                    DropdownMenuItem(onClick = { /* Handle refresh click */ }) {
                        Text(text = "User")
                    }
                    DropdownMenuItem(onClick = { /* Handle refresh click */ }) {
                        Text(text = "client")
                    }
                    DropdownMenuItem(onClick = { /* Handle refresh click */ }) {
                        Text(text = "about")
                    }
                }
            }

        )

    }

    fun ColumnScope.DropdownMenuItem(onClick: () -> Unit, interactionSource: @Composable () -> Unit) {

    }


    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "roomName")
        Text(text = "roomType")
        Text(text = "roomPrice")
        val roomName = null




        TextField(value = roomName,
            onValueChange = {}
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(color = Color.Green)
                .fillMaxWidth()
        )
        {
            val roomImageUrl = null
            Image(
                painter = rememberAsyncImagePainter(roomImageUrl),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Book")
        }
    }}

    @Composable
    @Preview(showBackground = true)
    fun ViewRoomScreenPreview(){
        ViewRoomScreen(rememberNavController())
    }

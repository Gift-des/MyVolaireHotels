package com.example.myvolairehotels.ui.theme.screens.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.example.myvolairehotels.navigation.ROUT_ADDROOM
import com.example.myvolairehotels.navigation.ROUT_VIEWCLIENTS
import com.example.myvolairehotels.navigation.ROUT_VIEWROOM
import com.example.myvolairehotels.navigation.ROUT_VIEWROOMUSER


@Composable
fun HomeScreen(navController: NavController){
    Column {
        TopBar()
        ContentScreen(navController = navController)
        BottomAppBarWork()
    }
}
@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen(rememberNavController())
}
@Composable
fun BottomAppBarWork() {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = {  }) {
                        Icon(Icons.Filled.Search, contentDescription = "search")

                    }
                    Spacer(modifier = Modifier.width(40.dp))
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            Icons.Filled.FavoriteBorder,
                            contentDescription = "Localized description",
                        )
                    }
//
                    Spacer(modifier = Modifier.width(40.dp))
                    IconButton(onClick = {  }) {
                        Icon(
                            Icons.Filled.MailOutline,
                            contentDescription = "Localized description",
                        )
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {  },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(Icons.Filled.AccountCircle, "Localized description")
                    }
                }
            )
        },
    ) { innerPadding ->
        Text(
            modifier = Modifier.padding(innerPadding),
            text = ""
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {

    val context = LocalContext.current.applicationContext
    var expanded by remember {
        mutableStateOf(false)
    }
    TopAppBar(title = {Text(text = "Volaire", fontFamily = FontFamily.Cursive, fontSize = 50.sp,
        fontWeight = FontWeight.Bold)},
        navigationIcon ={
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Home, contentDescription ="Home", tint = Color.Blue )

            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White ,
            titleContentColor = Color.Black ,
            navigationIconContentColor = Color.White
        ),
        actions = {
            IconButton(onClick = { Toast.makeText(context,"You have clicked the search icon",Toast.LENGTH_SHORT).show()}) {
                Icon(imageVector = Icons.Filled.Notifications, contentDescription = "notification", tint = Color.DarkGray)
            }

            IconButton(
                onClick = { expanded = true }
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "menu",
                    tint = Color.Black
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text(text = "View Clients") },
                    onClick = {
                        Toast.makeText(context, "Loading...", Toast.LENGTH_LONG).show()
                        val navController = null
                        navController.navigate(ROUT_VIEWCLIENTS)
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "Add Room") },
                    onClick = {
                        Toast.makeText(context, "Loading...", Toast.LENGTH_LONG).show()
                        val navController = null
                        navController.navigate(ROUT_ADDROOM)
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "View Rooms") },
                    onClick = {
                        Toast.makeText(context, "Loading...", Toast.LENGTH_LONG).show()
                        val navController = null
                        navController.navigate(ROUT_VIEWROOM)
                    }
                )

            }
        }

    )

}

private fun Nothing?.navigate(routeView: String) {

}

@Composable
fun ContentScreen(navController: NavController){
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,) {
        Card(modifier = Modifier
            .size(400.dp, 400.dp)
            .padding(10.dp),
            elevation = CardDefaults.cardElevation(), shape = CardDefaults.shape, colors = CardDefaults.cardColors(
                Color.Cyan)) {
            Text(text = "Perform tasks easily:", fontSize = 40.sp,
                color = Color.Black, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Cursive)
            Spacer(modifier = Modifier.height(20.dp))
            Column (horizontalAlignment = Alignment.CenterHorizontally){
                Button(
                    onClick = { navController.navigate(ROUT_ADDROOM) },
                    colors = ButtonDefaults.buttonColors(Color.Black)

                ) {
                    Text(text = "Add Room", fontSize = 30.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { navController.navigate(ROUT_VIEWROOMUSER)},
                    colors = ButtonDefaults.buttonColors(Color.Black)

                ) {
                    Text(text = "View Rooms", fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { navController.navigate(ROUT_VIEWROOM) },

                    colors = ButtonDefaults.buttonColors(Color.Black)
                )
                {
                    Text(text = "View Bookings", fontSize = 30.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
                }
            }
        }
    }
}


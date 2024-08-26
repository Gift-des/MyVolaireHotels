package com.example.myvolairehotels.ui.theme.screens.clients

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myvolairehotels.R

@Composable
fun ThankyouClientScreen(navController: NavController){
    Box(modifier = Modifier.height(180.dp).width(200.dp),
        contentAlignment = Alignment.Center) {

        Image(painter = painterResource(id = R.drawable.thanks) , contentDescription = "home",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)


    }




}


@Composable
@Preview(showBackground = true)
fun ThankyouClientScreenPreview(){
    ThankyouClientScreen(rememberNavController())
}
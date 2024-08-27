package com.example.myvolairehotels.navigation


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myvolairehotels.ui.theme.screens.clients.AddClientScreen
import com.example.myvolairehotels.ui.theme.screens.clients.ThankyouClientScreen
import com.example.myvolairehotels.ui.theme.screens.clients.UpdateClientScreen

import com.example.myvolairehotels.ui.theme.screens.clients.ViewClientsScreen
import com.example.myvolairehotels.ui.theme.screens.register.RegisterScreen
import com.example.myvolairehotels.ui.theme.screens.home.HomeScreen
import com.example.myvolairehotels.ui.theme.screens.login.LoginScreen
import com.example.myvolairehotels.ui.theme.screens.room.AddRoomScreen

import com.example.myvolairehotels.ui.theme.screens.room.ViewRoomScreen
import com.example.myvolairehotels.ui.theme.screens.room.ViewRoomUserScreen
import com.example.myvolairehotels.ui.theme.screens.splash.SplashScreen


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination:String = ROUT_SPLASH
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {


        composable(ROUT_SPLASH) {
            SplashScreen(navController = navController)
        }


        composable(ROUT_REGISTER) {
            RegisterScreen(navController = navController)
        }
        composable(ROUT_LOGIN) {
            LoginScreen(navController = navController)
        }

        composable(ROUT_HOME) {
            HomeScreen(navController = navController)
        }
        composable(ROUT_HOME) {
            HomeScreen(navController = navController)
        }
        composable(ROUT_ADDCLIENT) {
            AddClientScreen(navController = navController)
        }
        composable(ROUT_THANKYOU) {
            ThankyouClientScreen(navController = navController)
        }
        composable(ROUT_UPDATECLIENT) {
            UpdateClientScreen(navController = navController)
        }


        composable(ROUT_VIEWCLIENTS) {
            ViewClientsScreen(navController = navController)
        }

        composable(ROUT_VIEWCLIENT) {
            ViewClientsScreen(navController = navController )
        }

        composable(ROUT_ADDROOM) {
            AddRoomScreen(navController = navController)
        }


        composable(ROUT_VIEWROOM) {
            ViewRoomScreen(navController = navController)
        }
        composable(ROUT_VIEWROOMUSER) {
            ViewRoomUserScreen(navController = navController)
        }
        composable(ROUT_UPDATEROOM) {
            UpdateRoomScreen(navController = navController)
        }










    }
}

private fun AnimatedContentScope.UpdateRoomScreen(navController: NavHostController) {
    TODO("Not yet implemented")
}

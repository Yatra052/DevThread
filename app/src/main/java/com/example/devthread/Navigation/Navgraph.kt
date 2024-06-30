package com.example.devthread.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.internal.composableLambda
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.devthread.Screens.AddThread
import com.example.devthread.Screens.BottomNav
import com.example.devthread.Screens.Home
import com.example.devthread.Screens.Login
import com.example.devthread.Screens.Notification
import com.example.devthread.Screens.OtherUsers
import com.example.devthread.Screens.Profile
import com.example.devthread.Screens.Registration
import com.example.devthread.Screens.Search
import com.example.devthread.Screens.Splash

@Composable

fun Navgraph(navController: NavHostController)
{


    NavHost(navController = navController, startDestination = Routes.Splash.routes) {



        composable(Routes.Splash.routes){
            Splash(navController)
        }


        composable(Routes.Home.routes){
            Home(navController)
        }

        composable(Routes.Search.routes)
        {
            Search(navController)
        }

        composable(Routes.AddThread.routes){
            AddThread(navController)
        }

        composable(Routes.Notification.routes){
            Notification()
        }

        composable(Routes.Profile.routes){
            Profile(navController)
        }

        composable(Routes.BottomNav.routes){
            BottomNav(navController)
        }

        composable(Routes.Login.routes){
            Login(navController)
        }

        composable(Routes.Register.routes){
            Registration(navController)
        }

        composable(Routes.OtherUser.routes){
            val data = it.arguments!!.getString("data")
            OtherUsers(navController, data!!)
        }


    }

}

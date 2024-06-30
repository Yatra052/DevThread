package com.example.devthread.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.isPopupLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.devthread.Model.BottomNavItem
import com.example.devthread.Navigation.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNav(navController: NavHostController)
{

    val navController1 = rememberNavController()

    Scaffold(bottomBar = {MyBottomBar(navController1)}){ innerPadding ->


       NavHost(navController = navController1, startDestination = Routes.Home.routes,
           modifier = Modifier.padding(innerPadding)){


           composable(route = Routes.Home.routes){

               Home(navController)
           }

           composable(Routes.Search.routes)
           {
               Search(navController)
           }

           composable(Routes.AddThread.routes){
               AddThread(navController1)
           }

           composable(Routes.Notification.routes){
               Notification()
           }

           composable(Routes.Profile.routes){
               Profile(navController)
           }










    }


    }

}


@Composable
fun MyBottomBar(navController1: NavHostController){

    val backStackEntry = navController1.currentBackStackEntryAsState()

    val list = listOf(
        BottomNavItem(
            "Home",
            Routes.Home.routes,
            Icons.Rounded.Home
        ),

        BottomNavItem(
                "Search",
        Routes.Search.routes,
        Icons.Rounded.Search
    ),

        BottomNavItem(
            "Add Thread",
            Routes.AddThread.routes,
            Icons.Rounded.Add
        ),


        BottomNavItem(
            "Notification",
            Routes.Notification.routes,
            Icons.Rounded.Notifications
        ),


        BottomNavItem(
            "Profile",
            Routes.Profile.routes,
            Icons.Rounded.Person
        )


    )


    BottomAppBar {

        list.forEach{

            val selected = it.routes == backStackEntry.value?.destination?.route

             NavigationBarItem(selected = selected, onClick = {navController1.navigate(it.routes)},
                 icon = {
                     Icon(imageVector = it.icon, contentDescription = it.title)
                 })
        }
    }





}

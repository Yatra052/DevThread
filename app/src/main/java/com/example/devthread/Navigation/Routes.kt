package com.example.devthread.Navigation

sealed class Routes (var routes: String){

    object Home:Routes("Home")
    object Search:Routes("Search")
    object AddThread:Routes("AddThread")
    object Notification:Routes("Notification")
    object Profile :Routes("Profile")
    object BottomNav :Routes("BottomNav")
    object Splash :Routes("Splash")
    object Login :Routes("Login")
    object Register: Routes("Register")
    object OtherUser: Routes("OtherUser/{data}")
}
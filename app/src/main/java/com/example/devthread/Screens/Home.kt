package com.example.devthread.Screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.devthread.ItemView.ThreadItem
import com.example.devthread.Navigation.Routes
import com.example.devthread.Utils.SharedPref
import com.example.devthread.ViewModel.HomeViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlin.time.Duration.Companion.seconds

@Composable
fun Home(navHostController: NavHostController)
{

    val context = LocalContext.current
    val homeViewModel: HomeViewModel = viewModel()
    val threadAndUsers  by homeViewModel.threadAndUsers.observeAsState()




    LazyColumn {
        items(threadAndUsers ?: emptyList()){ pairs ->
                

            ThreadItem(thread = pairs.first, users = pairs.second, navHostController, FirebaseAuth.getInstance().currentUser!!.uid)
        }

    }

}


@Preview(showBackground = true)
@Composable
fun HomeScreen()
{
//    Home()
}
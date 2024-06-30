package com.example.devthread.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.devthread.Navigation.Routes
import com.example.devthread.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable
fun Splash(navController: NavHostController)
{

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (image) = createRefs()


        Image(painter = painterResource(id = R.drawable.threads), contentDescription = null,
            modifier = Modifier.constrainAs(image){
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

            })
    }


    LaunchedEffect(true) {
        delay(3000)

        if(FirebaseAuth.getInstance().currentUser != null) {
            navController.navigate(Routes.BottomNav.routes)
            {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop =  true

            }
        }
        else
        {
            navController.navigate(Routes.Login.routes)
            {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        }
    }



}
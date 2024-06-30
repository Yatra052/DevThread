package com.example.devthread.Screens

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter

import com.example.devthread.Navigation.Routes
import com.example.devthread.R
import com.example.devthread.ViewModel.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun Registration(navHostController: NavHostController)
{

    var name by remember {
        mutableStateOf("")
    }

    var username by remember {
        mutableStateOf("")
    }

    var bio by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var imageUri by remember{
        mutableStateOf<Uri?>(null)
    }

    val context = LocalContext.current

    val permissionToRequest = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){

        android.Manifest.permission.READ_MEDIA_IMAGES
    }

    else android.Manifest.permission.READ_EXTERNAL_STORAGE


    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
        uri: Uri? ->

        imageUri = uri

    }



    val permissionLauncher  = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {

        isGranted: Boolean ->

        if (isGranted){


        }
        else
        {

        }

    }


    val authViewModel: AuthViewModel = viewModel()

    val firebaseUser by authViewModel.firebaseUser.observeAsState(null)




    LaunchedEffect(firebaseUser) {

        if (firebaseUser != null)
        {
            navHostController.navigate(Routes.BottomNav.routes){
                popUpTo(navHostController.graph.startDestinationId)
                launchSingleTop = true
            }
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {



        Text(
            text = "Register", style = TextStyle(

                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp
            ),
            modifier = Modifier.padding(top = 10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Image(
            painter = if(imageUri  == null) painterResource(id = R.drawable.user)
            else rememberAsyncImagePainter(model = imageUri), contentDescription ="person",
            modifier = Modifier
                .width(90.dp)
                .height(90.dp)
                .clip(CircleShape)
                .clickable {
                    val isGranted = ContextCompat.checkSelfPermission(
                        context,
                        permissionToRequest
                    ) == PackageManager.PERMISSION_GRANTED

                    if (isGranted) {

                        launcher.launch("image/*")
                    } else {

                        permissionLauncher.launch(permissionToRequest)
                    }

                },
            contentScale = ContentScale.Crop
        )


        Box(modifier = Modifier.height(30.dp))


        OutlinedTextField(
            value = name, onValueChange = { name = it },
            shape = RoundedCornerShape(15.dp),
            label = {
                Text(text = "Name")
            }, keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ), singleLine = true,
            modifier = Modifier.width(330.dp)
        )


        Spacer(modifier = Modifier.height(8.dp))


        OutlinedTextField(
            value = username, onValueChange = {username = it },
            shape = RoundedCornerShape(15.dp),
            label = {
                Text(text = "Username")
            }, keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ), singleLine = true,
            modifier = Modifier.width(330.dp)
        )


        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = bio, onValueChange = { bio = it },
            shape = RoundedCornerShape(15.dp),
            label = {
                Text(text = "Bio")
            }, keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ), singleLine = true,
            modifier = Modifier.width(330.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email, onValueChange = { email = it },
            shape = RoundedCornerShape(15.dp),
            label = {
                Text(text = "Email")
            }, keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ), singleLine = true,
            modifier = Modifier.width(330.dp)
        )


        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password, onValueChange = { password = it },
            shape = RoundedCornerShape(15.dp),
            label = {
                Text(text = "Password")
            }, keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ), singleLine = true,
            modifier = Modifier.width(330.dp)
        )

        Spacer(modifier = Modifier.height(25.dp))

        ElevatedButton(
            onClick = {

                      if (name.isEmpty() || email.isEmpty() || bio.isEmpty() || password.isEmpty() || username.isEmpty() || imageUri == null)
                      {
                          Toast.makeText(context, "Please fill all details", Toast.LENGTH_SHORT).show()
                      }

                else{

                    authViewModel.register(name, username, bio, email, password, imageUri!!, context)

                      }










            }, modifier = Modifier.width(330.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        )
        {


            Text(
                text = "Register", style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                ),

                modifier = Modifier.padding(vertical = 6.dp)
            )

        }

        Spacer(modifier = Modifier.height(15.dp))

        TextButton(onClick = {
                             navHostController.navigate(Routes.Login.routes){
                                 popUpTo(navHostController.graph.startDestinationId)
                                 launchSingleTop = true
                             }

        }, modifier = Modifier.fillMaxWidth()) {


            Text(
                text = "Already registered? Login", style = TextStyle(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp
                ),
                color = Color.Black
            )

        }


    }



}


@Preview(showBackground = true)
@Composable
fun RegisterView()
{
//    Registration()
}
package com.example.devthread.ViewModel

import android.content.Context
import android.net.Uri
import androidx.compose.animation.core.snap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.devthread.Model.ThreadModel
import com.example.devthread.Model.UserModel
import com.example.devthread.Utils.SharedPref
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.UserDataWriter
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.protobuf.Value
import java.util.UUID

class SearchViewModel: ViewModel() {


    private val db = FirebaseDatabase.getInstance()
    val users = db.getReference("users")


    private var _users = MutableLiveData<List<UserModel>>()
    val userList:LiveData<List<UserModel>> = _users



    init {
        fetchUsers {
           _users.value =  it
        }
    }

    private fun fetchUsers(onResult:(List<UserModel>) -> Unit){

       users.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                val result = mutableListOf<UserModel>()

                for(threadSnapshot  in snapshot.children)
                {
                    val thread = threadSnapshot.getValue(UserModel::class.java)
                     result.add(thread!!)


                }

                onResult(result)


            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }


//    fun fetchUserFromThread(thread: ThreadModel, onResult: (UserModel)-> Unit){
//        db.getReference("users").child(thread.userId)
//            .addListenerForSingleValueEvent(object : ValueEventListener{
//                override fun onDataChange(snapshot: DataSnapshot) {
//
//                    val user = snapshot.getValue(UserModel::class.java)
//                    user?.let(onResult)
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//
//                }
//
//            })
//    }



}
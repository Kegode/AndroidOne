package com.example.morningmobileappmvvm.data

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavController
import com.example.morningmobileappmvvm.models.Client
import com.example.morningmobileappmvvm.navigation.ROUTE_LOGIN
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class ClientViewModel(var navController: NavController,
                      var context: Context) {
    var authRepository: AuthViewModel

    init {
        authRepository=AuthViewModel(navController,context)
        if (!authRepository.isloggedin()){
            navController.navigate(ROUTE_LOGIN)
        }
    }

    fun saveClient(
        filePath: Uri, firstname: String, lastname: String, gender: String, age:String,
        bio: String){
        var id = System.currentTimeMillis().toString()
        var storageReference = FirebaseStorage.getInstance().getReference().child("Passport/$id")

        storageReference.putFile(filePath).addOnCompleteListener{
            if (it.isSuccessful){
                storageReference.downloadUrl.addOnSuccessListener{
                    var imageUrl = it.toString()
                    var houseData = Client(imageUrl,firstname,lastname,gender,age,bio,id)
                    var dbRef = FirebaseDatabase.getInstance().getReference().child("Client/$id")
                    dbRef.setValue(houseData)
                    Toast.makeText(context,"Client added successfully",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(context,"${it.exception!!.message}",Toast.LENGTH_LONG).show()
            }
        }
    }

    fun viewClients(client:MutableState<Client>, clients: SnapshotStateList<Client>): SnapshotStateList<Client> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Client")


        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                clients.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(Client::class.java)
                    client.value = value!!
                    clients.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return clients
    }
}
package com.example.myvolairehotels.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.myvolairehotels.models.User
import com.example.myvolairehotels.navigation.ROUT_HOME
import com.example.myvolairehotels.navigation.ROUT_LOGIN
import com.example.myvolairehotels.navigation.ROUT_REGISTER

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthViewModel(var navController: NavController, var context: Context){

    var mAuth:FirebaseAuth
    val progress:ProgressDialog

    init {
        mAuth= FirebaseAuth.getInstance()
        progress=ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("PLease Wait.....")
    }
    fun register(name: String, email:String,pass:String,confpass:String){
        //progress.show()

        if (name.isBlank()||email.isBlank() || pass.isBlank() ||confpass.isBlank()){
            //progress.dismiss()
            Toast.makeText(context,"Please name, email and password cannot be blank",Toast.LENGTH_LONG).show()
            return
        }else if (pass != confpass){
            Toast.makeText(context,"Password do not match",Toast.LENGTH_LONG).show()
            return
        }else{
            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                if (it.isSuccessful){
                    val userdata= User(name,email,pass,mAuth.currentUser!!.uid)
                    val regRef=FirebaseDatabase.getInstance().getReference()
                        .child("Users/"+mAuth.currentUser!!.uid)
                    regRef.setValue(userdata).addOnCompleteListener {

                        if (it.isSuccessful){
                            Toast.makeText(context,"Registered Successfully",Toast.LENGTH_LONG).show()
                            navController.navigate(ROUT_HOME)

                        }else{
                            Toast.makeText(context,"${it.exception!!.message}",Toast.LENGTH_LONG).show()
                            navController.navigate(ROUT_LOGIN)
                        }
                    }
                }else{
                    navController.navigate(ROUT_REGISTER)
                }

            } }

    }
    fun login(email: String,pass: String){
        progress.show()

        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
            progress.dismiss()
            val id = System.currentTimeMillis().toString()
            if (it.isSuccessful){
                Toast.makeText(context,"Successfully Logged in",Toast.LENGTH_LONG).show()
                navController.navigate(ROUT_HOME)
//                navController.navigate(ROUTE_REGISTER)TO TAKE YOU TO A DIFFERENT PAGE
            }else{
                Toast.makeText(context,"${it.exception!!.message}",Toast.LENGTH_LONG).show()
                navController.navigate(ROUT_LOGIN)
            }
        }

    }
    fun logout(){
        mAuth.signOut()
        navController.navigate(ROUT_LOGIN)
    }
    fun isloggedin():Boolean{
        return mAuth.currentUser !=null
    }

}
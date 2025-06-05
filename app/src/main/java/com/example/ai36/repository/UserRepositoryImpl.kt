package com.example.ai36.repository

import com.example.ai36.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserRepositoryImpl : UserRepository {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    val database: FirebaseDatabase = FirebaseDatabase.getInstance()

    val ref: DatabaseReference = database.reference.child("users")

    override fun login(
        email: String,
        password: String,
        callback: (Boolean, String) -> Unit
    ) {
//        auth.currentUser.uid
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    callback(true, "Login successfully")
                } else {
                    callback(false, "${it.exception?.message}")

                }
            }
    }

    override fun register(
        email: String,
        password: String,
        callback: (Boolean, String, String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    callback(true, "Register successfully",
                        "${auth.currentUser?.uid}")
                } else {
                    callback(false, "${it.exception?.message}", "")

                }
            }
    }

    override fun addUserToDatabase(
        userId: String,
        model: UserModel,
        callback: (Boolean, String) -> Unit
    ) {
        //create -> setValue()
        //update -> updateChildren()
        //delete -> removeValue()
        ref.child(userId).setValue(model).addOnCompleteListener {
            if(it.isSuccessful){
                callback(true,"User added")
            }else{
                callback(false,"${it.exception?.message}")

            }
        }
    }

    override fun forgetPassword(
        email: String,
        callback: (Boolean, String) -> Unit
    ) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    callback(true,"Reset email sent to $email")
                }else{
                    callback(false,"${it.exception?.message}")

                }
            }
    }

    override fun deleteAccount(
        userId: String,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(userId).removeValue().addOnCompleteListener {
            if(it.isSuccessful){
                callback(true,"User deleted")
            }else{
                callback(false,"${it.exception?.message}")

            }
        }
    }

    override fun editProfile(
        userId: String,
        data: MutableMap<String, Any>,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(userId).updateChildren(data).addOnCompleteListener {
            if(it.isSuccessful){
                callback(true,"User edited")
            }else{
                callback(false,"${it.exception?.message}")

            }
        }
    }

    override fun getCurrentUser(): FirebaseUser? {
        TODO("Not yet implemented")
    }

    override fun getUserById(
        userId: String,
        callback: (Boolean, String, UserModel?) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun logout(callback: (Boolean, String) -> Unit) {
        TODO("Not yet implemented")
    }
}
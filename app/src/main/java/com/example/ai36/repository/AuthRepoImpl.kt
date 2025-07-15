package com.example.ai36.repository

import com.google.firebase.auth.FirebaseAuth

class AuthRepoImpl(var auth: FirebaseAuth) : AuthRepo {
    override fun login(
        email: String,
        password: String,
        callback: (Boolean, String) -> Unit
    ) {
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
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    callback(true, "register successfully",auth.currentUser?.uid.toString())
                } else {
                    callback(false, "${it.exception?.message}","")

                }
            }
    }
}
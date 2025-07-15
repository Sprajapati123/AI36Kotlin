package com.example.ai36.repository

interface AuthRepo {
    fun login(email: String, password: String, callback: (Boolean, String) -> Unit)

    fun register(email: String, password: String, callback: (Boolean, String, String) -> Unit)


}
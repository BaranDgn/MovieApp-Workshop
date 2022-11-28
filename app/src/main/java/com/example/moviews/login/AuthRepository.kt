package com.example.moviews.login

import com.example.moviews.util.Resource
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    //getting currently logged in user
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser>
    fun logout()
}
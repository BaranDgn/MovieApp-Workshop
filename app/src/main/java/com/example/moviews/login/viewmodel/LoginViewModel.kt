package com.example.moviews.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviews.login.AuthRepository
import com.example.moviews.util.Resource
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) :ViewModel() {

    //val loadingState = MutableStateFlow(LoadingState.IDLE)

    //access to authenticaion on firebase platform
    //private val auth : FirebaseAuth = Firebase.auth

    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow : StateFlow<Resource<FirebaseUser>?> = _loginFlow

    private val _signupFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signupFlow : StateFlow<Resource<FirebaseUser>?> = _signupFlow


    val currentUser: FirebaseUser?
        get()= repository.currentUser

    init{
        if(repository.currentUser != null){
            _loginFlow.value = Resource.Success(repository.currentUser!!)
        }
    }

   fun loginUser(email: String, password: String) = viewModelScope.launch {
       _loginFlow.value = Resource.Loading<FirebaseUser>()
       val result = repository.login(email, password)
       _loginFlow.value = result
   }

    fun signupUser(name:String, email: String, password: String) = viewModelScope.launch {
        _signupFlow.value = Resource.Loading<FirebaseUser>()
        val result = repository.signup(name, email, password)
        _signupFlow.value = result
    }

    fun logout(){
        repository.logout()
        _loginFlow.value = null
        _signupFlow.value = null
    }


}
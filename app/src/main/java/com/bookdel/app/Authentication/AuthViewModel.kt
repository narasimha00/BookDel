package com.bookdel.app.Authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel(): ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus() {
        _authState.value = if (auth.currentUser == null) AuthState.UnAuthenticated else AuthState.Authenticated
    }

    fun login(email: String, password: String) {
        if(email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error(message = "Kindly fill all the required fields !!")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                _authState.value = if (task.isSuccessful) AuthState.Authenticated else AuthState.Error(message = task.exception?.message?:"Something went wrong !")
            }
    }

    fun signup(email: String, password: String) {
        if(email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error(message = "Kindly fill all the required fields !!")
            return
        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                _authState.value = if (task.isSuccessful) AuthState.Authenticated else AuthState.Error(message = task.exception?.message?:"Something went wrong !")
            }
    }

    fun signOut() {
        auth.signOut()
        _authState.value = AuthState.UnAuthenticated
    }
}

sealed class AuthState {
    object Authenticated: AuthState()
    object UnAuthenticated: AuthState()
    object Loading: AuthState()
    data class Error(val message: String): AuthState()
}